from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    runtime_env: str = "local"
    runtime_service_name: str = "langgraph-runtime"
    runtime_log_level: str = "INFO"
    runtime_port: int = 7800

    class Config:
        env_prefix = "RUNTIME_"
        case_sensitive = False


settings = Settings()
