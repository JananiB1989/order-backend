Feature: the updates on order service
    Scenario: client makes call to POST /purchase-orders
        When the client creates /purchase-orders
        Then the client receives status code of 201
    Scenario: client makes call to GET /purchase-orders
        When the client calls /purchase-orders
        Then the client receives status code of 200
