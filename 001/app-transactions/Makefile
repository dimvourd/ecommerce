# Service Makefile

# Temp dir for Spring Boot
.PHONY: java21-cli-build
java21-cli-build:
	mkdir -p ./tmp
	mvn install -DtestFailureIgnore=true

.PHONY: build
build:
	mvn clean package -DskipTests

# Lab Run
.PHONY: lab-run
lab-run:
	mkdir -p ./tmp
	mvn compile
	mvn spring-boot:stop || true
ifeq ($(SERVICE_BG),true)
	# Background service: dynamic JMX port
	mvn spring-boot:start -Dspring-boot.run.jvmArguments="-Djava.io.tmpdir=./tmp -Dcom.sun.management.jmxremote.port=0" &
else
	# Starter service: foreground (Udemy detects this)
	mvn spring-boot:start -Dspring-boot.run.jvmArguments="-Djava.io.tmpdir=./tmp"
endif

# Convenient run (foreground)
.PHONY: run
run:
	mvn spring-boot:run

# Run jar dynamically
JAR = $(shell mvn help:evaluate -Dexpression=project.build.finalName -q -DforceStdout).jar

.PHONY: run-jar
run-jar: build
	java -jar target/$(JAR)

# Lab test: if FILE set, run that test; else run all
.PHONY: lab-test test
lab-test:
ifeq ($(FILE),)
	mvn test
else
	mvn -Dtest=$(subst /,.,$(subst src/test/java/,,$(basename $(FILE)))) test
endif

test:
	mvn test

.PHONY: clean
clean:
	mvn clean
