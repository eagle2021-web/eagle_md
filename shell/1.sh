#!/bin/bash
cur_swbom_env=''
if [[ -z ${SWBOM_ENV} ]]; then
  cur_swbom_env=${SWBOM_ENV}
fi
if [[ ${cur_swbom_env} = "" ]]; then
  echo 1
  ls \
  -a
else
  echo 2
  ls -a
fi