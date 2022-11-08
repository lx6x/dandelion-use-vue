package com.dandelion.gwt.domino.ui.shared.model;

/**
 * TODO 全局生命周期变量
 *
 * @author L-jf
 * @version 1.0
 * @date 2021/12/8 11:49
 */
public interface GlobalVariable {

    class Shell {

        private boolean is;

        private static final Shell SHELL =new Shell();

        private Shell(){

        }

        public static Shell getShell(){
            return SHELL;
        }

        public boolean isIs() {
            return is;
        }

        public void setIs(boolean is) {
            this.is = is;
        }
    }
}
