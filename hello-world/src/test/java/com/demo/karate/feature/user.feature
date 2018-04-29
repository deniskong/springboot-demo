Feature: Testing a REST API with Karate

Scenario: Testing valid GET endpoint
  Given url 'http://localhost:8080/api/helloWorld'
  When method GET
  Then status 200

