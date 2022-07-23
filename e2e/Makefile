cypress-open:
	yarn && npm run cypress
cypress-electron:
	docker-compose -f ../docker-compose.yaml -f docker-compose.yml up --exit-code-from e2e-electron e2e-electron
cypress-chrome:
	docker-compose -f ../docker-compose.yaml -f docker-compose.yml up --exit-code-from e2e-chrome e2e-chrome
cypress-firefox:
	docker-compose -f ../docker-compose.yaml -f docker-compose.yml up --exit-code-from e2e-firefox e2e-firefox
cypress-edge:
	docker-compose -f ../docker-compose.yaml -f docker-compose.yml up --exit-code-from e2e-edge e2e-edge
