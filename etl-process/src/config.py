import yaml

def __load_yaml__() -> dict:

    return yaml.safe_load(open("src/config.yml"))


def get(name: str) -> dict:

    yaml_file = __load_yaml__()

    return yaml_file[name]