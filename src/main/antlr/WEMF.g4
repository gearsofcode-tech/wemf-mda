grammar WEMF;

@header{
package tech.gearsofcode.wemf.parser;
}
prog: system epackage ;
system	 : 'system' ID ';';
epackage : 'package' packageName '{' (annotation* eclass)+ '}';
packageName : ID
		| ID '.' packageName;
eclass	 : 'class' ID '{' (annotation* attribute | annotation* method)* '}' #ConcreteClass
			|'abstract class' ID '{' (attribute | method)* '}' #AbstractClass
			| 'enum' ID '{' enumValues '}' #Enumeration
		;
attribute: ID ':' type cardinality? ';';
type	: (PRIMITIVE | WRAPPER | EXTERNAL | reference); 
PRIMITIVE: 'EBoolean' | 'EByte' | 'EChar' | 'EDouble' | 'EFloat' | 'EInt' | 'ELong' | 'EShort';
WRAPPER: 'EBooleanObject' | 'EByteObject' | 'ECharacterObject' | 'EDoubleObject' | 'EFloatObject' | 'EIntegerObject' | 'ELongObject' | 'EShortObject' | 'EByteArray';
EXTERNAL: 'EDate' | 'EBigInteger' | 'EBigDecimal';
reference: ID;		
method: ID '(' parameters? ')' (':' type |':' type cardinality)? ';' ;
parameters:	parameter (',' parameters)? ;
parameter: ID ':' type;
cardinality: '[' INT '..' (INT|'*') ']';
annotation: '@' ID ('(' STRINGLITERAL ')')?;
enumValues: enumValue (',' enumValues)? ;
enumValue: ID '(' STRINGLITERAL ')' ;


NEWLINE : [\r\n]+ ;
INT     : '-'? [0-9]+ ;
SHORT   : '-'? [0-9]+ 'S';
BYTE    : '-'? [0-9]+ 'B';
LONG    : '-'? [0-9]+ 'L';
FLOAT   : '-'? [0-9]+ '.' [0-9]+ 'F' ;
DOUBLE  : '-'? [0-9]+ '.' [0-9]+ 'D' ;
BOOLEAN : 'true' | 'false';
STRINGLITERAL
        : '\''
        (  EscapeSequence
        | ~('\\' | '\'' | '\r' | '\n')
        ) *
        '\'';

fragment
EscapeSequence
        : '\\' ('b' | 't' | 'n' | 'f' | 'r' | '\'' | '\"' | ('0'..'3')('0'..'7') | ('0'..'7')('0'..'7')|('0'..'7'));
ID      : ('a'..'z'|'A'..'Z'|'_'|[0-9])+;
WS      : (' ' | '\t' | '\n') {skip();};