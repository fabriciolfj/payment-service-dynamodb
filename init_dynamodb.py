import boto3
import json
import os

def initialize_dynamodb():
    # Configurar cliente DynamoDB Local
    dynamodb = boto3.client('dynamodb',
        endpoint_url='http://localhost:4566',
        region_name='us-east-1',
        aws_access_key_id='dummy',
        aws_secret_access_key='dummy'
    )

    # Diretório base do projeto
    base_path = os.path.join('src', 'main', 'resources', 'localstack', 'dynamo')

    # Criar tabelas
    create_path = os.path.join(base_path, 'create')
    for filename in os.listdir(create_path):
        if filename.endswith('.json'):
            with open(os.path.join(create_path, filename), 'r') as file:
                table_config = json.load(file)
                try:
                    dynamodb.create_table(**table_config)
                    print(f"Tabela criada com sucesso: {filename}")
                except Exception as e:
                    print(f"Erro ao criar tabela {filename}: {str(e)}")

    # Inserir dados
    insert_path = os.path.join(base_path, 'insert-data')
    for filename in os.listdir(insert_path):
        if filename.endswith('.json'):
            with open(os.path.join(insert_path, filename), 'r') as file:
                items = json.load(file)
                try:
                    dynamodb.batch_write_item(RequestItems=items)
                    print(f"Dados inseridos com sucesso: {filename}")
                except Exception as e:
                    print(f"Erro ao inserir dados {filename}: {str(e)}")

    print("Dynamo Initialized!")

if __name__ == "__main__":
    initialize_dynamodb()