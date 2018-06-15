# XMLAnalyzer

### Usage
java -jar smart-xml-analyzer-1.0-SNAPSHOT.jar from the root of the project.

It can receive 2 or 3 arguments, the first two arguments are **REQUIRED**, original first, diff second.
The third optional parameter is the name of the id of the element we want to compare.

### Strategy and assumptions
I assumed that comparing the attributes of the element and its value was the best strategy with the time given to get the element.

### Used JDK 1.8 and Maven to build the project.

### Sample runs:

java -jar target/smart-xml-analyzer-1.0-SNAPSHOT.jar /home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-0-origin.html /home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html
#document/html/body/div/div/div/div/div/div/a/

java -jar target/smart-xml-analyzer-1.0-SNAPSHOT.jar "/home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-0-origin.html" "/home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-2-container-and-clone.html"
#document/html/body/div/div/div/div/div/div/div/a/

java -jar target/smart-xml-analyzer-1.0-SNAPSHOT.jar "/home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-0-origin.html" "/home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-3-the-escape.html"
#document/html/body/div/div/div/div/div/div/a/

java -jar target/smart-xml-analyzer-1.0-SNAPSHOT.jar "/home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-0-origin.html" "/home/alan/Documentos/test/startbootstrap-sb-admin-2-examples/sample-4-the-mash.html"
#document/html/body/div/div/div/div/div/div/a/
