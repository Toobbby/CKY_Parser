# CKY_Parser
 CS2731 Homework 2: Implement a probabilistic CKY parser.        

### How to run         
#### Environment: 
Java 1.8         
#### Make sure you have:
the grammar file (i.e. pcfg.txt) with the same root of CKY_Parser.jar 

#### Access CKY_Parser folder:

```
cd CKY_Parser
```

            
#### Run:        
```
java -jar CKY_Parser.jar <grammar_file_name> <a test sentence> <gold standard parse of the test sentence>
```

### Binarization
For binarization, the program will split a CFG with more than 2 terms (no maximum) on the right side (TO side) to a series of CFGs which have 2 terms on the right side.        

#### Example

<b>S -> A B c</b> is binarized to <b>S -> A NEW0</b> and <b>NEW0 -> B c</b>.

#### New Symbol Generation
I implemented a SymbolGenerator to help to generate new symbols (NEW0, NEW1, NEW2 ...). New symbol will only be generated when is needed. (I have implemented checking logic, if there is already a new symbol generated for a certain pair of terms, the system will not create a duplicate one.)



### Reports        
#### Viterbi_fag24.pdf: 
Answer for problem 2.1 HMM Decoding        
#### report_fag24.pdf: 
System output for 2 example sentences and analysis       

### Issues         

#### The code has no known issue. If you have any question, please contact <fag24@pitt.edu>     
