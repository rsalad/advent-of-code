package aoc2023.util

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class FileReaderTest {

  @Test
  fun `readLines -- file exists -- expected list returned`() {
    val reader = FileReader()

    val lines = reader.readLines("fileReaderTest.txt")

    assertThat(lines).containsExactly("first", "second", "", "last")
  }

  @Test
  fun `readLines -- file does not exist -- exception thrown`() {
    val reader = FileReader()

    assertThatThrownBy { reader.readLines("fakeFile.txt") }
      .isInstanceOf(FileNotFound::class.java)
  }
}