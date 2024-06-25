import backoff
import openai
from openai import OpenAI


class OpenAIClient:
    MODEL = "gpt-4o"

    system_prompt: str

    def __init__(self, system_prompt):
        self.client = OpenAI()
        self.system_prompt = system_prompt

    @backoff.on_exception(backoff.expo, openai.RateLimitError)
    def get_tests_for_class(self, java_class):
        chat_completion = self.client.chat.completions.create(
            model=self.MODEL,
            messages=[
                {"role": "system",
                 "content": self.system_prompt},
                {"role": "user", "content": java_class}
            ]
        )

        return chat_completion.choices[0].message.content
