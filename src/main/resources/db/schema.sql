--DROP TABLE IF EXISTS stock_fund_transaction;

CREATE TABLE stock (
    id SERIAL PRIMARY KEY,
    ticker VARCHAR(10) NOT NULL CHECK ticker <> ''
);

CREATE TABLE stock_fund (
  id SERIAL PRIMARY KEY,
  ticker VARCHAR(10) NOT NULL CHECK ticker <> '',
  name VARCHAR(100) NOT NULL CHECK name <> ''
);

CREATE TABLE stock_fund_transaction (
    id SERIAL PRIMARY KEY,
    found_id INT REFERENCES stock_fund (id),
    fund_weight VARCHAR(100) NOT NULL CHECK name <> ''
);

