import numpy as np
from scipy.stats import ranksums
from tabulate import tabulate
import json
import os
import matplotlib.pyplot as plt

GPT_RESULTS_PATH = 'result_analysis/gpt-4o_results/'
GPT_FIXED_RESULTS_PATH = 'result_analysis/gpt-4o_fixed_results/'
EVOSUITE_RESULTS_PATH = 'result_analysis/evosuite_results/'

GPT_RUN_COUNT = 10
EVOSUITE_RUN_COUNT = 6


def load_results(src_path, target_dict, run_count):
    index = 0
    for filename in os.listdir(src_path):
        file_path = os.path.join(src_path, filename)
        if os.path.isfile(file_path):
            with open(file_path, 'r') as file:
                data = json.load(file)

                for key, value in data.items():
                    if key not in target_dict:
                        target_dict[key] = np.zeros(run_count)

                    target_dict[key][index] = value
                index += 1


def vargha_delaney_a(x, y):
    m = len(x)
    n = len(y)
    rank_sum = np.sum([np.sum(y < xi) + 0.5 * np.sum(y == xi) for xi in x])
    a_measure = rank_sum / (m * n)
    return a_measure


def process_data():
    gpt_results = dict()
    gpt_fixed_results = dict()
    evosuite_results = dict()

    load_results(EVOSUITE_RESULTS_PATH, evosuite_results, EVOSUITE_RUN_COUNT)
    load_results(GPT_RESULTS_PATH, gpt_results, GPT_RUN_COUNT)
    load_results(GPT_FIXED_RESULTS_PATH, gpt_fixed_results, GPT_RUN_COUNT)

    create_comparison_table(gpt_results, evosuite_results, "________GPT-4o vs EvoSuite________")
    create_comparison_table(gpt_fixed_results, evosuite_results,
                            "________GPT-4o with manually fixed tests vs EvoSuite________")

    create_mean_increase_chart(gpt_results, gpt_fixed_results)


def create_comparison_table(gpt_results, evosuite_results, title):
    print("\n" + title)

    table = []

    for key, value in gpt_results.items():
        gpt_avg = np.mean(value)
        gpt_std = np.std(value)
        evosuite_avg = np.mean(evosuite_results[key])
        evosuite_std = np.std(evosuite_results[key])

        w_stat, p_value_signed = ranksums(value, evosuite_results[key])
        vd_a = vargha_delaney_a(value, evosuite_results[key])

        data = [key, gpt_avg, gpt_std, evosuite_avg, evosuite_std, w_stat, p_value_signed, vd_a]
        table.append(data)
        # This line is for copypasting to the paper in LaTeX
        # print(f'{key}: {gpt_avg:.2f} & {gpt_std:.2f} & {evosuite_avg:.2f} & {evosuite_std:.2f} & {w_stat:.2f} & {p_value_signed:.2f} & {vd_a:.2f}\\\\')


    print(tabulate(table,
                   headers=['Key', 'GPT Avg', 'GPT Std', 'Evosuite Avg', 'Evosuite Std', 'W Stat', 'P Value Signed',
                            'Vd A'], tablefmt='plain', stralign='left', floatfmt='.3f'))

    totals = [np.mean([row[i] for row in table]) for i in range(1, len(table[0]))]
    print(totals)


def create_mean_increase_chart(gpt_results, gpt_fixed_results):
    labels = []
    values = []
    for key, value in gpt_results.items():
        labels.append(key.rsplit('.', 1)[1])
        values.append(np.mean(gpt_fixed_results[key]) - np.mean(value))

    plt.bar(labels, values)
    plt.xticks(rotation=45, ha='right')
    plt.tight_layout()
    plt.show()


if __name__ == '__main__':
    process_data()
