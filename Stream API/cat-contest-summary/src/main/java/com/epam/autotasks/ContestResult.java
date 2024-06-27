package com.epam.autotasks;

import lombok.Getter;

@Getter
public class ContestResult {

    private final Integer running;
    private final Integer jumping;
    private final Integer purring;
    private final Integer sum;

    public ContestResult(Integer running, Integer jumping, Integer purring) {
        this.running = running;
        this.jumping = jumping;
        this.purring = purring;
        this.sum = countResults(running, jumping, purring);
    }

    public Integer countResults(Integer running, Integer jumping, Integer purring) {
        return running + jumping + purring;
    }
}
