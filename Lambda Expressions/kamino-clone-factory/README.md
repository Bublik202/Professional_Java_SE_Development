# Kamino Clone Factory Task

The goal of this task is to practice using capture variables, suppliers, and mutability awareness.

Duration: _1.5 hours_

## Description

Even though we don't have anything about Kamino in our archives, it still exists and has to function properly
in order to provide the Republic with a brand-new army of clones.

Your goal is to provide policies for creating and equipping batches of clones.

In this task, you already have several classes. Let's take a look at them:

1. [`Equipment`][Equipment] stores information about clones' equipment (weapon, armor, etc.).
2. [`EquipmentFactory`][EquipmentFactory] has only one method, which allows for ordering the same equipment as provided,
   so it's something of a clone factory itself.
3. [`CloneTrooper`][CloneTrooper] represents a clone (its code, nickname, and equipment).
4. [`KaminoFactory`][KaminoFactory] allows a new clone to be grown with the desired code and a batch of clones to be formed.

These classes must be used in this task. Please notice that some fields are `final`
and these classes are either mutable or utility.

Now, let's investigate the class you're going to implement - [`BatchPolicies`][BatchPolicies].
Here, you have the nested interface `BatchPolicy`, for which you will provide implementations.
This interface accepts a clone as a base and the number of clones in the batch.

Also, you might notice a few methods in `BatchPolicies` itself. Your goal is to implement these methods.
Let's describe what the requirements are for each of them.

## Requirements

We never pass `null` to any of these methods, so you don't have to check for it.
However, classes like `CloneTrooper` or `Equipment` might have `null` fields, so keep this in mind.

### `getCodeAwarePolicy`

This method must return a `BatchPolicy` that generates new clones that are exact copies of the passed base clone,
except for its `code`. The `code` of each new clone must be generated using the parameters `codePrefix` and `seed`,
which are passed to the method as arguments. The new code must start with the prefix followed by the `-` symbol
and finish with the seed value formatted to show at least four symbols.
The seed value for each new clone must be incremented by **1**.

So, the following call of this method must produce the result below:

```java
BatchPolicies.getCodeAwarePolicy("TT", 42).formBatchOf(clone, 3)
```
```
{
  Clone{"TT-42", ...},
  Clone{"TT-43", ...},
  Clone{"TT-44", ...}
}
```

All other fields must be the same as those in the base clone.

### `addNicknameAwareness`

You're probably wondering why we copy unique characteristics like `nickname` in the previous example.
How can `TT-42` and `TT-43` have the same nickname if this is something you have to earn? We couldn't agree more!
This is why we need to implement another method that decorates the passed `BatchPolicy`
and add a functionality for awarding a `nickname`.

Please note, that passing `BatchPolicy` allows us to reuse any other possible `BatchPolicy`,
which might come in handy for testing.

In addition to policy, this decorating function accepts `Iterator<String>`.
This iterator provides a `nickname` for each new clone. It might not have enough nicknames for all clones,
but remember that not every clone deserves a nickname.
If not, the correct action would be not to give the clone a nickname.

The following call must produce the result below:
```java
BatchPolicies.addNicknameAwareness(iterator, policy).formBatchOf(base, 5);
// iterator: Rex, Cody, Omega
```
```
{
    Clone{..., "Rex", ...},
    Clone{..., "Cody", ...},
    Clone{..., "Omega", ...},
    Clone{..., null, ...},
    Clone{..., null, ...}
}
```

Other fields must be configured by the `BatchPolicy` provided.

### `addEquipmentOrdering`

If you think about it, we're just copying the clone's equipment. Make sense, right?
But what if we change our base clone's equipment? The equipment of all the batches based on him would change.
Now this doesn't make sense at all, right? To resolve this issue, we create a copy of the equipment
instead of making a reference assignment to the same object. That's what this method is about.
You just create a copy of the passed equipment for each new clone.
All other fields must be configured by the `BatchPolicy` provided.

## Examples

```java
Iterator<String> nicks = List.of("Rex", "Cody").iterator();
CloneTrooper jango = KaminoFactory.growClone("Jango Fett");
Equipment commanderEquip = new Equipment();
commanderEquip.setArmor("Phase 1");
commanderEquip.setWeapon("DC-15A");

CloneTrooper[] batch = BatchPolicies.addEquipmentOrdering(commanderEquip,
  BatchPolicies.addNicknameAwareness(nicks,BatchPolicies.getCodeAwarePolicy("TT", 42))
  .formBatchOf(jango, 3);

assert batch.size == 3;
assert batch[0].getCode().equals("TT-42");
assert batch[1].getCode().equals("TT-43");
assert batch[2].getCode().equals("TT-44");
assert batch[0].getNickname().equals("Rex");
assert batch[1].getNickname().equals("Cody");
assert batch[2].getNickname() == null;
assert batch[0].getEquipment().equals(commanderEquipment);
assert batch[0].getEquipment() != commanderEquipment;
assert batch[1].getEquipment().equals(commanderEquipment);
assert batch[1].getEquipment() != commanderEquipment;
assert batch[2].getEquipment().equals(commanderEquipment);
assert batch[2].getEquipment() != commanderEquipment;
assert batch[0].getEquipment() != batch[1].getEquipment();
assert batch[0].getEquipment() != batch[2].getEquipment();
assert batch[1].getEquipment() != batch[2].getEquipment();
```

[Equipment]: src/main/java/edu/epam/fop/lambdas/kamino/equipment/Equipment.java

[EquipmentFactory]: src/main/java/edu/epam/fop/lambdas/kamino/equipment/EquipmentFactory.java

[CloneTrooper]: src/main/java/edu/epam/fop/lambdas/kamino/CloneTrooper.java

[KaminoFactory]: src/main/java/edu/epam/fop/lambdas/kamino/KaminoFactory.java

[BatchPolicies]: src/main/java/edu/epam/fop/lambdas/kamino/BatchPolicies.java
