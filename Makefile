wrapper:
	gradle wrapper
buildw-app:
	gradle clean build test publishToMavenLocal
buildw:
	cd international-airports-rest/international-airports-model && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd international-airports-rest/international-airports-data && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd international-airports-rest/international-airports-service-api && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd international-airports-sst/international-airports-sst-client/international-airports-sst-client-common && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd international-airports-sst/international-airports-sst-client/international-airports-sst-client-airports && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd international-airports-sst/international-airports-sst-client/international-airports-sst-client-webcam && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	gradle clean build test publishToMavenLocal
upgrade:
	gradle wrapper --gradle-version 7.4
upgrade-mac-os:
	brew upgrade gradle
	sdk install gradle
