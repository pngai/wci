package intermediate.symtabImpl;

import intermediate.SymTabKey;

/**
 * Created by patrick on 2016/5/11.
 */

/**
 * <h1>SymTabKeyImpl</h1>
 *
 * <p>Attribute keys for a symbol table entry.</p>
 */
public enum  SymTabKeyImpl implements SymTabKey {
    //constant.
    CONSTANT_VALUE,

    //Procedure or funvtion.
    ROUTINE_CODE, ROUTINE_SYMTAB, ROUTINE_ICODE, ROUTINE_PARMS, ROUTINE_ROUTINES,

    //variable or record field value.
    DATA_VALUE
}
