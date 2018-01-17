Run tests

mvn clean test

To generate report in Allure

mvn site

To open allure report

mvn -Djetty.port=8083 jetty:run 