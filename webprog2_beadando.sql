-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Ápr 17. 21:02
-- Kiszolgáló verziója: 10.4.8-MariaDB
-- PHP verzió: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `webprog2_beadando`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `description` varchar(500) COLLATE utf8_hungarian_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `image`) VALUES
(1, 'D1 kategória', 'A motorkerékpár, valamint a legfeljebb 3,5 tonna megengedett legnagyobb össztömegű, legfeljebb 7 személy szállítására alkalmas személygépkocsi és annak pótkocsija.', 'd1category.jpg'),
(2, 'D2 kategória', 'Valamennyi olyan gépkocsi, amely nem tartozik egyéb díjkategóriába, és külön jogszabály alapján nem minősül útdíjköteles gépjárműnek. (Ha a gépjármű forgalmi engedélyében, a J (Járműkategória) mezőben N1 besorolás szerepel, akkor az adott jármű – a szállítható személyek számától függetlenül – D2 díjkategóriába tartozik.)', 'd2category.jpg'),
(3, 'B2 kategória', 'Autóbusz: személyszállítás céljára készült olyan gépkocsi, amelyben a vezető ülését is beleértve 9-nél több állandó ülőhely található.', 'b2category.png'),
(26, 'U kategória', 'A D2 és a B2 díjkategóriába tartozó járművek vontatmánya.', 'ucategory.jpg');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `countries`
--

CREATE TABLE `countries` (
  `id` int(11) NOT NULL,
  `nationality_mark` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `country` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `countries`
--

INSERT INTO `countries` (`id`, `nationality_mark`, `country`) VALUES
(1, 'H', 'Magyarország'),
(2, 'A', 'Ausztria'),
(3, 'D', 'Németország'),
(4, 'GB', 'Egyesült Királyság'),
(17, 'I', 'Olaszország'),
(19, 'CH', 'Svájc'),
(20, 'SLO', 'Szlovénia'),
(21, 'SK', 'Szlovákia'),
(22, 'RO', 'Románia');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `license_plates`
--

CREATE TABLE `license_plates` (
  `id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `size` varchar(500) COLLATE utf8_hungarian_ci NOT NULL,
  `format` varchar(500) COLLATE utf8_hungarian_ci NOT NULL,
  `picture1` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `picture2` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `more_info` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `license_plates`
--

INSERT INTO `license_plates` (`id`, `country_id`, `size`, `format`, `picture1`, `picture2`, `more_info`) VALUES
(1, 1, 'Az egysoros 520 mm * 110 mm (európai szabvány); a kétsoros: 280 mm * 200 mm; motorkerékékpáron: 240 mm * 130 mm. Különleges esetekben elöl használható öntapadós matrica, melynek mérete: 300 mm * 75 mm.', 'Fényvisszaverős fehér alapon fekete karakterek, fekete keret; latinbetűs írás; kódolás: € AAA-999; sorozatszámok növekedési sorrendje: 654-321. A hátsó rendszámon a kötőjel felett egy műszaki felülvizsgálati évente változó színű érvényesítő matricája, alatta pedig a környezetvédelmi felülvizsgálat zöld vagy sárga érvényesítő matricája található. 2004. május 1. óta a rendszám bal szélén egy 40 mm széles függőleges kék sávban felül az Európai Unió zászlaja, alatta fehér színnel az H betű látható.', 'hungary1.jpg', 'hungary2.jpg', 'http://plates.gaja.hu/h/index.html'),
(2, 2, 'Méretei megfelelnek az európai szabványnak: 520 mm * 120 mm; bár a kétsoros változat kis mértékben eltér attól: 200 mm * 300 mm (nem EU-s sorozat mérete: 270 mm * 200 mm); motorkerékpárokon: 2002 november előtt: 270 mm * 200 mm, 2003. 11.-2005.03.: 200 mm * 250 mm, 2005 áprilistól: 210 mm * 170 mm.', 'Fényvisszaverős fehér alapon fekete karakterek, keret nélkül; latin betűs írás; kódolás: a standard formátum € T*999AA vagy € TT*999AA (területkódok itt), de a sorozatszám helyén gyakorlatilag maximum hat karakterig bármi állhat; sorozatszámok növekedési sorrendje: alapesetben 32154; a területkód és a számok között a szövetségi tartomány címere, alatta a teljes neve kapott helyet; 2002-től a bal szélén egy 40 mm széles függőleges kék sávban felül az Európai Unió zászlaja, alatta fehérrel az A fe', 'austria1.jpg', 'austria2.jpg', 'http://plates.gaja.hu/a/index.html'),
(4, 3, 'Megfelelnek az európai szabványnak (1994-es sorozat): az egysoros 520 mm * 110 mm; a kétsoros: 340 mm * 200 mm, motorkerékpár: 255 mm * 130 mm, moped: 240 mm * 130 mm.', 'Fényvisszaverős fehér alapon fekete karakterek, fekete keret; latinbetűs írás; kódolás: €T*A@ &&&9 vagy €TT*A@ &&&9 vagy €TTT*A@ &&9 (területkódok itt); sorozatszámok növekedési sorrendje: 65 4321. 1994-től a rendszám bal szélén egy 40 mm széles függőleges kék sávban felül az Európai Unió zászlaja, alatta fehér színnel a D felirat is megtalálható.', 'germany1.jpg', 'germany2.jpg', 'http://plates.gaja.hu/d/index.html'),
(5, 4, 'Általában: elöl 520 mm * 111 mm; hátul 520 mm * 111 mm vagy 285 mm * 203 mm vagy 533 mm * 152 mm; ettől eltérő méretben is találhatóak rendszámok, mert a szabvány a méretet nem határozza meg.', 'Fényvisszaverős, elöl fehér, hátul sárga alapon fekete karakterek, keret nélkül; latinbetűs írás; kódolás: € TTDD AAA (területkódok itt; dátumkódok itt); sorozatszámok növekedési sorrendje: 321. A rendszám bal szélén egy 40 mm széles függőleges kék sávban felül az Európai Unió zászlaja, alatta fehér színnel az GB betű látható. Az Euroband nem kötelező része a rendszámnak!', 'gb1.jpg', 'gb2.jpg', 'http://plates.gaja.hu/gb/index.html'),
(14, 17, '360 mm * 110 mm; hátul az egysoros: 520 mm * 110 mm, a kétsoros: 297 mm * 214 mm.', 'Fényvisszaverős fehér alapon fekete karakterek, fekete keret; latinbetűs írás; kódolás: € AA*999AA €; sorozatszámok növekedési sorrendje: 76 32154. A rendszám bal szélén egy 40 mm széles függőleges kék sávban felül az Európai Unió zászlaja, alatta fehér színnel az I betű látható. A rendszám jobb szélén egy 40 mm széles függőleges kék sávban felül egy okkersárga körben a rendszám kiadási évének utolsó két számjegye okkersárgával, alatta fehér színnel a kétkarakteres területkód látható.', 'italy1.jpg', 'italy2.jpg', 'asd');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'Doe', 'john@example.com'),
(2, 'Doe', 'john@example.com'),
(3, 'ragoa', ''),
(4, 'ragoa', ''),
(5, 'asd', 'asd'),
(6, 'aa', 'aa'),
(7, 'a', 'a'),
(8, 'wasd', '123'),
(9, 'ííí', 'ííí'),
(10, 'aasasa', '123'),
(11, 'asdsasad', '123'),
(12, 'aaaaaa', '123'),
(13, 'xycxxyc', '123'),
(14, 'yyxyx', '123'),
(15, '', '');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `license_plates`
--
ALTER TABLE `license_plates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_country_id` (`country_id`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT a táblához `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT a táblához `license_plates`
--
ALTER TABLE `license_plates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `license_plates`
--
ALTER TABLE `license_plates`
  ADD CONSTRAINT `FK_country_id` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
