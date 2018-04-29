Feature: Testing a REST API with Karate

Background:
  * url 'http://localhost:8080'

Scenario: Testing valid GET endpoint
  Given path 'api/hello'
  When method GET
  Then status 200

Scenario: Testing a POST endpoint with request body
  Given url 'http://localhost:8080/api/hello'
  And request { name: 'John' , email: 'John.Smith@gmail.com'}
  When method POST
  Then status 201
  And match $ == read('response.json')
  And assert responseTime < 1000
  And print 'response = ' +  karate.pretty(response)

Scenario: Testing a POST endpoint with request body and read
  * path 'api/hello'
  * request read('request.json')
  * method POST
  * status 201
  * match $ == read('response.json')
  * assert responseTime < 1000
  * print 'response = ' +  karate.pretty(response)