// ============================================================================
//   Copyright 2006, 2007 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.maths;

import java.util.List;
import org.testng.annotations.Test;

/**
 * Unit test for the {@link CombinationGenerator} class.
 * @author Daniel Dyer
 */
public class CombinationGeneratorTest
{
    @Test
    public void testCombinationGenerator()
    {
        String[] elements = new String[]{"1", "2", "3"};
        CombinationGenerator<String> generator = new CombinationGenerator<String>(elements, 2);
        assert generator.hasMore() : "Generator should have more combinations available.";
        assert generator.getTotalCombinations() == 3 : "Possible combinations should be 3.";
        assert generator.getRemainingCombinations() == 3: "Remaining combinations should be 3.";

        String[] combination1 = generator.nextCombinationAsArray();
        assert generator.hasMore() : "Generator should have more combinations available.";
        assert combination1.length == 2 : "Combination length should be 2.";
        assert generator.getRemainingCombinations() == 2: "Remaining combinations should be 2.";
        assert !combination1[0].equals(combination1[1]) : "Combination elements should be different.";

        List<String> combination2 = generator.nextCombinationAsList(); // Use different "next" method to exercise other options.
        assert generator.hasMore() : "Generator should have more combinations available.";
        assert combination2.size() == 2 : "Combination length should be 2.";
        assert generator.getRemainingCombinations() == 1: "Remaining combinations should be 1.";
        // Make sure this combination is different from the previous one.
        assert !combination2.get(0).equals(combination2.get(1)) : "Combination elements should be different.";
        assert !(combination1[0] + combination1[1]).equals(combination2.get(0) + combination2.get(1))
            : "Combination should be different from previous one.";

        String[] combination3 = new String[2];
        generator.nextCombinationAsArray(combination3); // Use different "next" method to exercise other options.
        assert !generator.hasMore() : "Generator should have no more combinations available.";
        assert combination3.length == 2 : "Combination length should be 2.";
        assert generator.getRemainingCombinations() == 0: "Remaining combinations should be 0.";
        // Make sure this combination is different from the others generated.
        assert !combination3[0].equals(combination3[1]) : "Combination elements should be different.";
        assert !(combination2.get(0) + combination2.get(1)).equals(combination3[0] + combination3[1])
            : "Combination should be different from previous one.";
        assert !(combination1[0] + combination1[1]).equals(combination3[0] + combination3[1])
            : "Combination should be different from previous one.";
    }
}
