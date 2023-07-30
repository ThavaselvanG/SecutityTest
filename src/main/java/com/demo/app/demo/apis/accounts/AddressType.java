package com.demo.app.demo.apis.accounts;

  public enum AddressType {
    HOME {
        @Override
        public String toString() {
            return "Home";
        }
    }, WORK {
        @Override
        public String toString() {
            return "Work";
        }
    }
}
