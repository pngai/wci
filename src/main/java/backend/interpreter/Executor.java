package backend.interpreter;

import backend.Backend;
import intermediate.ICode;
import intermediate.SymTab;
import intermediate.SymTabStack;
import message.Message;
import message.MessageType;

/**
 * Created by patrick on 2016/5/10.
 */
public class Executor extends Backend{
    /**
     * Process the intermediate code and the symbol table generated by the parser to execute the source program.
     * @param iCode the intermediate code.
     * @param symTabStack the symbol table.
     * @throws Exception if an error occurred.
     */
    public void process(ICode iCode, SymTabStack symTabStack) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        int executionCount = 0;
        int runtimeErrors = 0;
        sendMessage(new Message(MessageType.INTERPRETER_SUMMARY, new Number []{executionCount,
                runtimeErrors, elapsedTime
        }));
    }
}
