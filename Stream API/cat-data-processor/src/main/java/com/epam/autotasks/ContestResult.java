package com.epam.autotasks;

import java.util.Objects;

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
    
    

    @Override
    public String toString() {
        return "running: " + running + "\n" + "jumping: " + jumping + "\n" + "purring: " + purring;
    }

	@Override
	public int hashCode() {
		return Objects.hash(jumping, purring, running, sum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContestResult other = (ContestResult) obj;
		return Objects.equals(jumping, other.jumping) && Objects.equals(purring, other.purring)
				&& Objects.equals(running, other.running) && Objects.equals(sum, other.sum);
	}
}
