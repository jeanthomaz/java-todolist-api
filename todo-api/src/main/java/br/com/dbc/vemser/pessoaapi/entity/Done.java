package br.com.dbc.vemser.pessoaapi.entity;

public enum Done {

    T(1),
    F(2);

    private Integer done;

    Done(Integer done) {
        this.done = done;
    }

    public Integer getDone() {
        return done;
    }

}
