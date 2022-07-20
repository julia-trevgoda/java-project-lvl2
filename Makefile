run-dist:
	./build/install/app/bin/app

lint:
	./gradlew checkstyleMain checkstyleTest

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build