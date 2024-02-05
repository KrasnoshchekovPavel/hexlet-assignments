package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReversedSequenceTest {
    @Test
    void testReversedSequenceTest(){
        String testing_text = "abcdef";
        CharSequence text = new ReversedSequence(testing_text);
        assertThat(text.toString()).isEqualTo("fedcba");
        assertThat(text.charAt(1)).isEqualTo('e');
        assertThat(text.length()).isEqualTo(6);
        assertThat(text.subSequence(1, 4)).isEqualTo("edc");
    }
}
