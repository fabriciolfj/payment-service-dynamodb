#! /bin/bash
#use more files to put more than 25 items: https://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_BatchWriteItem.html
for filename in /localstack/dynamo/create/*.json; do

  awslocal dynamodb create-table --cli-input-json "file://$filename" --output json

done

for filename in /localstack/dynamo/insert-data/*.json; do

  awslocal dynamodb batch-write-item --request-items "file://$filename" --output json

done

echo "Dynamo Initialized!"
