FILENAME=
ENCODING=-encoding utf-8
SRC=src
CLASSDIRECTORY=bin
APP=Acme
all:
	javac -sourcepath $(SRC) $(SRC)/control/$(APP).java $(ENCODING) -d $(CLASSDIRECTORY)
	java -cp $(CLASSDIRECTORY) control.$(APP) $(FILENAME)
test:
	javac -sourcepath $(SRC) $(SRC)/control/$(APP).java $(ENCODING) -d $(CLASSDIRECTORY)
	java -cp $(CLASSDIRECTORY) tests.control.Test$(APP)
	cat $(SRC)/tests/inputTest