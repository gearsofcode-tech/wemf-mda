grammar NAIL;

@header{
package tech.gearsofcode.nail.parser;
}

prog: import_declaration entity_declaration rules_declaration validation_declaration;

import_declaration : 'IMPORT' ID '.wemf' ;

entity_declaration : 'ENTITY' ID ;

rules_declaration : 'RULES-SECTION' rule_declaration+ 'END-RULES-SECTION';

rule_declaration : ('RULE'|'UPDATE-RULE'|'DELETE-RULE') ID ON_FIELD variable (',' variable)* 'ON ERROR' STRINGLITERAL defined? constraints? ;
	

defined : 'DEFINED AS' STRINGLITERAL ;

constraints: 'CONSTRAINTS' constraint+ 'END-CONSTRAINTS' ;
constraint : required 
			| unique
			| matches
		   	| max_value
		   	| max_length
		   	| assert_true
		   	| assert_false
		   	| integer_digits
		   	| decimal_digits;
		   	
assert_true : 'ASSERT-TRUE' boolean_expr;

assert_false : 'ASSERT-FALSE' boolean_expr;		   	

boolean_expr : 'NOT' boolean_expr #Negation 
			 | boolean_expr OPERATOR boolean_expr #BinaryExpression
			 | (variable | STRINGLITERAL | INTEGER) #ValueExpression
			 | REPOSITORY_FUNCTION '(' variable (',' variable)* ')' #RepositoryFunction
			 | FUNCTION '(' variable ')' #FunctionExpression
			 | '(' boolean_expr ')' #GroupExpression; 

variable : ID
		 | ID ('.' ID)+; 

unique : 'UNIQUE';   	

matches : 'MATCHES' STRINGLITERAL;

required : 'REQUIRED';

max_value : 'MAX-VALUE' INTEGER;

max_length : 'MAX-LENGTH' INTEGER;

integer_digits : 'INTEGER-DIGITS' INTEGER;

decimal_digits : 'DECIMAL-DIGITS' INTEGER;

validation_declaration : validate_on_insert? validate_on_update? validate_on_delete? ;

validate_on_insert: 'VALIDATE-ON-INSERT' ID+ 'END-VALIDATE-ON-INSERT';

validate_on_update: 'VALIDATE-ON-UPDATE' ID+ 'END-VALIDATE-ON-UPDATE';

validate_on_delete: 'VALIDATE-ON-DELETE' ID+ 'END-VALIDATE-ON-DELETE';

FUNCTION : 'IS-NULL' | 'IS-NOT-NULL'  | 'IS-EMPTY' | 'IS-NOT-EMPTY';

OPERATOR : 'DIVIDED'
		 | 'TIMES'
		 | 'PLUS'
		 | 'MINUS'
		 | 'AND'
		 | 'OR'
		 | 'XOR'
		 | 'IMPLIES'
		 | 'EQUALS' 
		 | 'GREATER-THAN' 
		 | 'GREATER-EQUALS' 
		 | 'LESS-THAN' 
		 | 'LESS-EQUALS' 
		 | 'NOT-EQUALS';

ON_FIELD : 'ON FIELD' | 'ON FIELDS';
STRINGLITERAL
        : '\'' (ESC |.)*? '\'';
ESC 	: '\\\\';        
INTEGER	: DIGIT+;       
DIGIT	: [0-9] ;        
ID      : ('a'..'z'|'A'..'Z'|'_'|[0-9])+;
REPOSITORY_FUNCTION : 'REPOSITORY-' ('A'..'Z'|'-')+;
WS      : (' ' | '\t' | '\r'|'\n') {skip();};