from dotenv import load_dotenv

from experiment_runner import ExperimentRunner

if __name__ == "__main__":
    load_dotenv()


ExperimentRunner().run()