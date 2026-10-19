# Root Makefile
SERVICES = app-account app-transactions

# Required Udemy build
.PHONY: java21-cli-build
java21-cli-build:
	@for s in $(SERVICES); do \
		echo "🔨 building $$s"; \
		$(MAKE) -C $$s java21-cli-build || $(MAKE) -C $$s build; \
	done

# Required placeholder (Udemy)
.PHONY: java21-cli-run
java21-cli-run: ;

# Udemy Lab Run
.PHONY: lab-run
lab-run:
	@echo "Starting starter service (app-account) in foreground..."
	@$(MAKE) -C app-account lab-run
	@echo "Starting background services..."
	@for s in $(SERVICES); do \
		if [ "$$s" != "app-account" ]; then \
			$(MAKE) -C $$s lab-run & \
		fi \
	done
	@sleep 1
	@echo "All start commands issued."

# Lab Test (Udemy IDE Test button)
.PHONY: lab-test
lab-test:
ifeq ($(FILE),)
	@echo "No file open: running all tests for all services..."
	@for s in $(SERVICES); do \
		echo " -> testing $$s"; \
		$(MAKE) -C $$s lab-test; \
	done
else
	@echo "FILE is set: $(FILE)"
	@if echo "$(FILE)" | grep -q "^app-account/"; then \
		$(MAKE) -C app-account lab-test FILE=$(FILE); \
	elif echo "$(FILE)" | grep -q "^app-transactions/"; then \
		$(MAKE) -C app-transactions lab-test FILE=$(FILE); \
	else \
		echo "Open file not inside a service; running all tests instead."; \
		for s in $(SERVICES); do $(MAKE) -C $$s lab-test; done; \
	fi
endif

# Convenience targets
.PHONY: build run test clean
build:
	@for s in $(SERVICES); do $(MAKE) -C $$s build; done

run:
	@for s in $(SERVICES); do $(MAKE) -C $$s run & done
	@echo "Started services (background)"

test:
	@for s in $(SERVICES); do $(MAKE) -C $$s test; done

clean:
	@for s in $(SERVICES); do $(MAKE) -C $$s clean; done
