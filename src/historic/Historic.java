package src.historic;

import java.util.Stack;

public class Historic <T> {
    private Stack<T> undo;
    private Stack<T> redo;
    private T atual;

    public Historic() {
        this.undo  = new Stack<T>();
        this.redo = new Stack<T>();
        this.atual = null;
    }

    public void add(T e) {
        undo.add(e);
        redo.clear();
        this.atual = e;
    }

    public T undo() {
        if (!this.undo.isEmpty()) {
            this.atual = undo.pop();
            redo.add(atual);
        }

        return this.atual;
    }

    public T redo() {
        if (!this.redo.isEmpty()) {
            this.atual = redo.pop();
            undo.add(this.atual);
        }

        return this.atual;
    }

    public T getAtual() {
        return this.atual;
    }
}
