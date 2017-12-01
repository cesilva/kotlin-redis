#!/bin/bash

cf create-service rediscloud 30mb redis-db
cf bind-service kotlin-redis redis-db
cf restage kotlin-redis
