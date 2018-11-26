# moneytransfer
RESTful API for money transfers between accounts.

## Features

- Depositing: It was necessary for setting up money on the accounts. Depositing will also create account if it not exist to make simpler.

- Transfering: Will try to transfer from one account to another. It requires existing accounts enough available balance.

- Get balance: Will return the balance. It was needed for ensuring that the the transfering worked.

## Exception handling

AccountCreatedException happens when the account is created. It returns status code 201.

AccountNotFoundException happens when it was suposed to the account to exist but don't. Returns 404.

## Improvements

Make the NotEnoughBalanceException (403 - Forbidden) case to return message explaining that the user is out of balance.

Force two digits on inputs, throwing 404 when receive numbers with more than two digits of precision.
