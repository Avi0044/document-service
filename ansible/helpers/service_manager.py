#! /usr/bin/python3
import shutil
import sys
from pathlib import Path
import yaml


def read_file(file, file_type='yaml'):
    if file_type == 'yaml':
        with open(f"{file}", 'r') as _file:
            file_content = yaml.safe_load(_file)
    elif file_type == 'text':
        with open(f"{file}", 'r') as _file:
            file_content = _file.read()
    return file_content


def replace_in_file(file, text_to_replace, replace_with_text, in_place=False):
    with open(f'{file}', 'r') as _file:
        updated_file = _file.read()
    updated_file = updated_file.replace(text_to_replace, replace_with_text)
    if in_place:
        return updated_file
    with open(f'{file}', "w") as _file:
        _file.write(updated_file)


project_path = sys.argv[1]
env_type = sys.argv[2]
set_port_in_service = sys.argv[3]
deployment_file = f'{project_path}/jenkins/{env_type}-deploy-config.yaml'
build_file = f'{project_path}/jenkins/build-config.yaml'

deploy_config = read_file(deployment_file)
build_config = read_file(build_file)

service_flags = ' '.join(deploy_config['serviceFlags'])

service_sample_file = f"{project_path}/ansible/services/{build_config['appName']}.service"

if set_port_in_service == "true":
    service_file_path = f"{project_path}/ansible/services/{build_config['appName']}_{deploy_config['servicePort']}.service"
else:
    service_file_path = service_sample_file

shutil.copyfile(f'{service_sample_file}.txt', service_file_path)
replace_in_file(service_file_path, '@@SERVICE_FLAGS@@', service_flags)
replace_in_file(service_file_path, '@@SERVICE_PORT@@', deploy_config['servicePort'])
