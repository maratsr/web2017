CREATE DATABASE web2017_dev;
CREATE DATABASE web2017_test;
CREATE DATABASE web2017_prod;

CREATE USER springuser WITH password 'qwerty';

GRANT ALL privileges ON DATABASE web2017_dev TO springuser;
GRANT ALL privileges ON DATABASE web2017_test TO springuser;
GRANT ALL privileges ON DATABASE web2017_prod TO springuser;