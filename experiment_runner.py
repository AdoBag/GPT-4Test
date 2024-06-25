import time

from file_processor import FileProcessor
from openai_client import OpenAIClient


class ExperimentRunner:
    RUN_COUNT = 10
    SYSTEM_PROMPT = "You are a helpful assistant that can create robust test suites for Java classes. You will output code only."

    openai_client = OpenAIClient(SYSTEM_PROMPT)
    file_processor = FileProcessor()

    def run(self):
        classes = self.file_processor.read_classes_in_src_dir()

        for i in range(self.RUN_COUNT):
            print(f'Run {i + 1} started')
            run_start_time = time.time()
            for class_name, class_contents in classes.items():
                message = self.openai_client.get_tests_for_class(class_contents)
                self.file_processor.write_run_results(class_name, i + 1, message)

            run_end_time = time.time()
            print(f'Run {i + 1} finished in {round(run_end_time - run_start_time, 2)} seconds')

        self.file_processor.clone_src_classes(classes, self.RUN_COUNT)
