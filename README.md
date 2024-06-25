By Adomas Bagdonas

This is a part of a Bachelor's Research Project (2023-24 Q4) of TU Delft.

## Important notes

- This Python project contains a Java Gradle project inside the TestExecutor folder.
- If you have any questions feel free to reach out to `adobag@gmail.com`
- The research paper is also included with the repository

## To run the project

- Create a pipenv environment and activate it
- Install the requirements from `Pipfile`
- Get an API key from OpenAI and create a `.env` file with `OPENAI_API_KEY=<your OpenAI API key>`
- Upload your files in `TestExecutor/src/main/java/org/example/run1`
  - Copy and paste the classes in `../example/run<number>` for as many runs as you need
  - If there are any dependencies, place them in the dependencies folder
- Build the inner Java project by running `./gradlew build` in `TestExecutor`
- Tweak the experiment parameters if needed in `experiment_runner.py`
- Run `main.py`
- The generated classes will be in `TestExecutor/src/test/java/org/example/..`
- To obtain mutation scores, run `./gradlew pitest` in `TestExecutor`

## Project structure

- The folder `paper experiment results` contains a few interesting items from the original experiment:
    - The initial report of the failing tests
    - The first report of Pitest after removing the failing tests
    - The report of Pitest after fixing the failing tests
    - The raw responses of GPT-4o
    - The results that can be dynamically analysed
    - A screenshot of runtimes
- The raw responses will be stored in `raw_replies`
- For reproducibility, I included my data in `data_analysis` folder, so you could rerun it with the same (or with your own) data
