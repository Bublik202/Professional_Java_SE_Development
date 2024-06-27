package edu.epam.fop.lambdas.kamino;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.epam.fop.lambdas.kamino.equipment.Equipment;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests on cloning policies")
class KaminoFactoryTest {

  private static final CloneTrooper JANGO_FETT = new CloneTrooper("");
  private static final String BAD_BATCH_ERROR = "You formed a bad batch";

  @Test
  @DisplayName("Tests that clones might be created with certain auto-incremented codes")
  void shouldCreateClonesWithCertainCodes() {
    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.getCodeAwarePolicy("ARC", 5555),
        JANGO_FETT, 10);
    CloneTrooper[] expectedBatch = IntStream.range(5555, 5565)
        .mapToObj(i -> clone(String.format("ARC-%04d", i), null, null))
        .toArray(CloneTrooper[]::new);
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
  }

  @Test
  @DisplayName("Tests that clones are created with rightly formatted codes")
  void shouldCreateClonesWithProperlyFormattedCodes() {
    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.getCodeAwarePolicy("CT", 1),
        JANGO_FETT, 1);
    CloneTrooper[] expectedBatch = {
        clone("CT-0001", null, null)
    };
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
  }

  @Test
  @DisplayName("Tests that clones might be created with nicknames")
  void shouldCreateClonesWithNicknames() {
    List<String> nicknames = List.of(
        "Boba Fett",
        "Rex",
        "Cody",
        "Hunter",
        "Omega",
        "Appo",
        "Gree",
        "Davijaan",
        "Bacara",
        "Wrecker"
    );
    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.addNicknameAwareness(nicknames.iterator(),
            BatchPolicies.getCodeAwarePolicy("CC", 1010)),
        JANGO_FETT, nicknames.size()
    );
    CloneTrooper[] expectedBatch = IntStream.range(1010, 1010 + nicknames.size())
        .mapToObj(i -> clone(String.format("CC-%04d", i), nicknames.get(i - 1010), null))
        .toArray(CloneTrooper[]::new);
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
  }

  @Test
  @DisplayName("Tests that clones still might be created even if there is no imagination for their nicknames")
  void shouldNotSetNicknamesIfThereIsNotEnoughNicknames() {
    List<String> nicknames = List.of("X1", "X2");
    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.addNicknameAwareness(nicknames.iterator(),
            BatchPolicies.getCodeAwarePolicy("Beta", 1409)),
        JANGO_FETT, 3);
    CloneTrooper[] expectedBatch = {
        clone("Beta-1409", "X1", null),
        clone("Beta-1410", "X2", null),
        clone("Beta-1411", null, null)
    };
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
  }

  @Test
  @DisplayName("Tests that clones still might be created even if there is too much imagination for their nicknames")
  void shouldSkipNicknamesWhenBatchIsLess() {
    InputStream resource = getClass().getClassLoader().getResourceAsStream("nicknames.txt");
    assertNotNull(resource, "Nicknames file not found");
    Iterator<String> scanner = new Scanner(resource);
    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.addNicknameAwareness(scanner,
            BatchPolicies.getCodeAwarePolicy("RC", 1207)),
        JANGO_FETT, 5);
    CloneTrooper[] expectedBatch = {
        clone("RC-1207", "A'den", null),
        clone("RC-1208", "Able", null),
        clone("RC-1209", "Ace", null),
        clone("RC-1210", "Aeon", null),
        clone("RC-1211", "Amp", null)
    };
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
  }

  @Test
  @DisplayName("Tests that clones might be created with the provided equipment")
  void shouldProvideBatchWithEquipment() {
    Equipment equipment = new Equipment();
    equipment.setArmor("Katarn-class Commando");
    equipment.setWeapon("DC-17m IWS");

    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.addEquipmentOrdering(equipment,
            BatchPolicies.getCodeAwarePolicy("TK", 9091)),
        JANGO_FETT, 25);
    CloneTrooper[] expectedBatch = IntStream.range(9091, 9116)
        .mapToObj(i -> clone(String.format("TK-%04d", i), null, equipment))
        .toArray(CloneTrooper[]::new);
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
  }

  @Test
  @DisplayName("Tests that clones does not share the same equipment with each other")
  void shouldHaveCopiesOfEquipment() {
    Equipment equipment = new Equipment();
    equipment.setArmor("Clone Training");
    equipment.setWeapon("DC-15A");

    CloneTrooper[] batch = KaminoFactory.formBatch(BatchPolicies.addEquipmentOrdering(equipment,
            BatchPolicies.getCodeAwarePolicy("Alpha", 1)),
        JANGO_FETT, 1);
    Equipment copyEquipment = new Equipment();
    copyEquipment.setArmor(equipment.getArmor());
    copyEquipment.setWeapon(equipment.getWeapon());
    CloneTrooper[] expectedBatch = {
        clone("Alpha-0001", null, copyEquipment)
    };
    assertArrayEquals(expectedBatch, batch, BAD_BATCH_ERROR);
    equipment.setWeapon("DC-15X");
    equipment.setArmor("Night Ops");
    assertArrayEquals(expectedBatch, batch, "Equipment must not be modifiable outside of the batch");
  }

  private static CloneTrooper clone(String code, String nickname, Equipment equipment) {
    CloneTrooper trooper = new CloneTrooper(code);
    trooper.setNickname(nickname);
    trooper.setEquipment(equipment);
    return trooper;
  }

}
