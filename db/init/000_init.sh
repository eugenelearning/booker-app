#!/usr/bin/env bash

set -e

psql -v ON_ERROR_STOP=1 -U "${POSTGRES_USER}" <<-EOF
  CREATE DATABASE auth;
  CREATE DATABASE booker;
  CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
  CREATE EXTENSION IF NOT EXISTS pgcrypto;
EOF