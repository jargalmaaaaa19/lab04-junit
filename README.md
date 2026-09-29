# Лаборатори №4: Нэгжийн тестийн эхлэл — JUnit 5

- **Нэр:** З.Жаргалмаа
- **Оюутны код:** B232270005
- **Хичээл:** F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест (2026)

## Орчин

`java -version`: openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment (build 17.0.20.1+1-1-24.04-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-24.04-Ubuntu, mixed mode, sharing)
`mvn -version`: Apache Maven 3.8.7
Maven home: /usr/share/maven
Java version: 17.0.20.1, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.6.87.2-microsoft-standard-wsl2", arch: "amd64", family: "unix"  
## Тестийн тоо

- Тестийн методын тоо: **14** (12 `@Test` + 2 `@ParameterizedTest`)
- `results/mvn-test.txt` дахь `Tests run`: **24**, Failures: 0, Errors: 0, Skipped: 0, `BUILD SUCCESS`
- 24 = 12 энгийн тест + `letterGradeBoundaries`-ийн 8 мөр + `totalScoreSums`-ийн 4 мөр

## Мутацийн үр дүн

`letterGrade` доторх `score >= 90` нөхцөлийг зориуд `score > 90` болгосон.
`results/mvn-test-mutant.txt`: `Tests run: 24, Failures: 2`, `BUILD FAILURE`.

Унасан тестүүд:
1. `ninetyIsExactlyA` — `expected: <A> but was: <B>`
2. `letterGradeBoundaries[2]` (CSV-ийн `"90,A"` мөр) — `expected: <A> but was: <B>`

Дараа нь `>= 90` болгож буцаасан, `results/mvn-test.txt` дахин ногоон болсон.

## Дүгнэлт

Энэ лабораторид JUnit 5, Maven ашиглан GradeCalculator классыг тестэлсэн. Ердийн утга, хязгаарын утга, буруу оролтыг тусад нь тест болгож, AAA бүтэц болон @DisplayName ашигласан. Хамгийн сонирхолтой нь мутацийн туршилт байлаа: 90 оноог шалгадаг ганц хязгаарын тест байгаагүй бол `>= 90` -> `> 90` өөрчлөлт илрэхгүй байх байсан. Ердийн 95->A тест мутацийг илрүүлж чадаагүй, учир нь 95 нь хоёр нөхцөлд хоёуланд A өгдөг. Мөн 100 оноотой тест ч мутацид унасангүй. Үүнээс pass болсон тест нь зөв тест гэсэн үг биш, хязгаарын утгыг заавал шалгах ёстой гэдгийг ойлгосон. Parameterized тест нь олон хязгаарыг цөөн кодоор хамарч, Surefire мөр бүрийг тусдаа тест гэж тоолдог гэдгийг мэдэж авсан.
