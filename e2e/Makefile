cypress-open:
	yarn && npm run cypress
cypress-electron: install-linux
	docker-compose -f ../docker-compose.yml -f docker-compose.yml up --exit-code-from e2e-electron e2e-electron
cypress-chrome: install-linux
	docker-compose -f ../docker-compose.yml -f docker-compose.yml up --exit-code-from e2e-chrome e2e-chrome
cypress-firefox: install-linux
	docker-compose -f ../docker-compose.yml -f docker-compose.yml up --exit-code-from e2e-firefox e2e-firefox
cypress-edge: install-linux
	docker-compose -f ../docker-compose.yml -f docker-compose.yml up --exit-code-from e2e-edge e2e-edge
install-linux:
	sudo apt-get update
	sudo apt-get install libgtk2.0-0 libgtk-3-0 libgbm-dev libnotify-dev libgconf-2-4 libnss3 libxss1 libasound2 libxtst6 xauth xvfb libva-intel-vaapi-driver -y
