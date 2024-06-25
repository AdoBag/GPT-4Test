import os


class FileProcessor:
    SRC_PATH = 'TestExecutor/src/main/java/org/example/'
    TEST_PATH = 'TestExecutor/src/test/java/org/example/'
    RAW_REPLIES_PATH = 'raw_replies/'

    def read_classes_in_src_dir(self):
        classes = dict()

        for filename in os.listdir(self.SRC_PATH + 'run1/'):
            file_path = os.path.join(self.SRC_PATH + 'run1/', filename)
            if os.path.isfile(file_path):
                with open(file_path, 'r') as file:
                    print(f"Reading file: {filename}")
                    classes[filename] = file.read()

        return classes

    def write_run_results(self, class_name, run_number, content):
        test_class_name = self.add_test_suffix(class_name)

        raw_replies_dir = self.RAW_REPLIES_PATH + f'run{run_number}/'
        if not os.path.exists(raw_replies_dir):
            os.makedirs(raw_replies_dir)

        with open(raw_replies_dir + test_class_name, 'w', encoding='utf-8') as file:
            file.write(content)

        extracted_code = self.extract_code(content)
        test_code = self.replace_package(extracted_code, run_number)

        test_result_dir = self.TEST_PATH + f'run{run_number}/'
        if not os.path.exists(test_result_dir):
            os.makedirs(test_result_dir)

        with open(test_result_dir + test_class_name, 'w', encoding='utf-8') as file:
            file.write(test_code)

    def clone_src_classes(self, classes, run_count):
        for i in range(run_count - 1):
            run_number = i + 2

            src_dir = self.SRC_PATH + f'run{run_number}/'
            if not os.path.exists(src_dir):
                os.makedirs(src_dir)

            for class_name, class_contents in classes.items():
                with open(src_dir + class_name, 'w', encoding='utf-8') as file:
                    file.write(self.replace_package(class_contents, run_number))

    @staticmethod
    def add_test_suffix(java_class_name):
        return java_class_name[:-5] + 'Test.java'

    @staticmethod
    def extract_code(content):
        """
        The code in the response is always separated by ```java and ```.
        """
        return content[7:].split('```')[0]

    @staticmethod
    def replace_package(content, run_number):
        return content.replace('org.example.run1;', f'org.example.run{run_number};')
