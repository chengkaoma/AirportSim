//Chengkao Ma
package sample;
import java.util.*;
import java.io.*;

public class Airport implements EventHandler {

    private int m_inTheAir;
    private int m_onTheGround;
    public boolean m_freeToLand;
    public boolean m_freeToTakeoff;
    private double m_flightTime;
    private double m_runwayTimeToLand;
    private double m_requiredTimeOnGround;
    private double m_runwayTimeToTakeOff;
    private String m_airportName;
    private int m_totalLandingPassenger;
    private int m_totalLeavingPassenger ;
    private int m_index;
    private double m_circlingTime;
    private double m_totalCirclingTime;
    public List<String> resultarray= new ArrayList<>();


    Queue<AirportEvent> LandingQueue = new LinkedList<AirportEvent>();
    Queue<AirportEvent> DepartingQueue = new LinkedList<AirportEvent>();

    private static int m_distance;



    public static String []AIRPORT = {"ATL", "SFO", "JFK", "ORD", "AUS","PHX","LAX","DFW","DEN","CLT",
            "LAS", "IAH", "MCO", "EWR", "MSP","MIA","DTW","BOS","SEA","LGA",
            "PHL", "TPA", "IAD", "BWI", "FLL","MDW","SLC","HNL","SAN","DCA",
            "PDX", "RSW", "STL", "MEM", "MCI","OAK","MSY","SNA","RDU","BNA",
            "CLE", "HOU", "SJC", "SMF", "SJU","AUS","SAT","PIT","DAL","IND",
            "MKE", "CMH", "OGG", "PBI", "CVG","TUS","BDL","BUF","KAX","RNO"};

    public static Integer DISTANCE[][]=

            {
                    {0, 1986, 1428, 1944, 2382, 1810, 2043, 1387, 2279, 2114, 2292, 1297, 2125, 2350, 2303, 1255, 1251, 1193, 1983, 2301, 1363, 2451, 1152, 1262, 2252, 1381, 2387, 1782, 1625, 2156, 1663, 1891, 2389, 1968, 2002, 2369, 1522, 1792, 2094, 1430, 1778, 1836, 2334, 2439, 2404, 1823, 1375, 1365, 1480, 1807, 2484, 2440, 2310, 1641, 1289, 1886, 1775, 1518, 1873, 1159},
                    {1986, 0, 2167, 2460, 2395, 1536, 1739, 2314, 2303, 1747, 1957, 1533, 2495, 2119, 1966, 1480, 1896, 1969, 2283, 1123, 1824, 2495, 2400, 1322, 2153, 2453, 1616, 2360, 1771, 1894, 2231, 1673, 1440, 1232, 2431, 2009, 1969, 1460, 2222, 2348, 2294, 1128, 1145, 1579, 2203, 1211, 2417, 1856, 1318, 1157, 1220, 1846, 1980, 1392, 1943, 1783, 1699, 1399, 2351, 1603},
                    {1428, 2167, 0, 1346, 2018, 1351, 2202, 2493, 1227, 2307, 2327, 1613, 1704, 2459, 1770, 1908, 1941, 2107, 1976, 1982, 1685, 2247, 2016, 1979, 1717, 1548, 1332, 2497, 1341, 2426, 1154, 1886, 2292, 2173, 1607, 2467, 1119, 1171, 2359, 2156, 2301, 1968, 2203, 1867, 2042, 1860, 1607, 2189, 2174, 2174, 1238, 2356, 2074, 1939, 2389, 2436, 1798, 1743, 1618, 1193},
                    {1944, 2460, 1346, 0, 1137, 2043, 2335, 2157, 1142, 1215, 1686, 1168, 2136, 2103, 1530, 1120, 1386, 1849, 2207, 1824, 1958, 1479, 1896, 2132, 1191, 2200, 2139, 2282, 2108, 1201, 1619, 2340, 1100, 1600, 1874, 1138, 1201, 2489, 1625, 2149, 1995, 1265, 1645, 1174, 1474, 1688, 2352, 2149, 1302, 1156, 1297, 1382, 1734, 2480, 2344, 1981, 1260, 1107, 2074, 1386},
                    {2382, 2395, 2018, 1137, 0, 1426, 2149, 2070, 1984, 2211, 2412, 2474, 1999, 1728, 1325, 2125, 1113, 2109, 2207, 1337, 2146, 1943, 2457, 1396, 1448, 1205, 1291, 1756, 1818, 1800, 2433, 1124, 1493, 2161, 1404, 1136, 2321, 2085, 2163, 1697, 2355, 1631, 1608, 1125, 1524, 1450, 1309, 1438, 1294, 1153, 2028, 1685, 2350, 2446, 1350, 2243, 1940, 1171, 2091, 1532},
                    {1810, 1536, 1351, 2043, 1426, 0, 2368, 1320, 1872, 1944, 1216, 1849, 1927, 2375, 1860, 2291, 2176, 2073, 1882, 1756, 1349, 1847, 1596, 2012, 2092, 1969, 1911, 2187, 2301, 1557, 1319, 2017, 1875, 1552, 2159, 1452, 1552, 2253, 2276, 1249, 2240, 1200, 2145, 1858, 1360, 1317, 2037, 1688, 1343, 1377, 2392, 1501, 1226, 1260, 1413, 1753, 2206, 1642, 1937, 1136},
                    {2043, 1739, 2202, 2335, 2149, 2368, 0, 1406, 2461, 1986, 1340, 2405, 2449, 1594, 2259, 1293, 1347, 1659, 2287, 1382, 1423, 2188, 1494, 2378, 2020, 1978, 1143, 2341, 1350, 1994, 1800, 1122, 2168, 1258, 1438, 2240, 1548, 1207, 1529, 1443, 1937, 1569, 2251, 1159, 1880, 1715, 1390, 1184, 2491, 1623, 1239, 2492, 2073, 1598, 1283, 1370, 1274, 1181, 1172, 2132},
                    {1387, 2314, 2493, 2157, 2070, 1320, 1406, 0, 1708, 1491, 1912, 1416, 1200, 2471, 1614, 1493, 2452, 2294, 1245, 1368, 1470, 2370, 2243, 2037, 2457, 1230, 1383, 1283, 1455, 1469, 2172, 1952, 2412, 2075, 1107, 2276, 1314, 2452, 2036, 1274, 1670, 2408, 1232, 1713, 2495, 2280, 1661, 1913, 1118, 2130, 1711, 1288, 1694, 1663, 1264, 2140, 1866, 2382, 2076, 1651},
                    {2279, 2303, 1227, 1142, 1984, 1872, 2461, 1708, 0, 2093, 1234, 2339, 1366, 2106, 2238, 1235, 2308, 1448, 2065, 2442, 2403, 2193, 2204, 1109, 2465, 1856, 1169, 1608, 1280, 1453, 1341, 1555, 1536, 2023, 1470, 2421, 1927, 2245, 2488, 2231, 1651, 2000, 2072, 2391, 2344, 2144, 1108, 1303, 2397, 2473, 1186, 1810, 1177, 1129, 1644, 1291, 2203, 1641, 1517, 1346},
                    {2114, 1747, 2307, 1215, 2211, 1944, 1986, 1491, 2093, 0, 1615, 1830, 1334, 1980, 2220, 1532, 1321, 1366, 2362, 2406, 1647, 1933, 1569, 1740, 1125, 1765, 2036, 1681, 1968, 1387, 1517, 1191, 2329, 2393, 1834, 2026, 2356, 2103, 1533, 1710, 2130, 2432, 1417, 1757, 1335, 2032, 2328, 1801, 2382, 2207, 1169, 1658, 1112, 2282, 1767, 1645, 1779, 2351, 1405, 2442},
                    {2292, 1957, 2327, 1686, 2412, 1216, 1340, 1912, 1234, 1615, 0, 2479, 2315, 2366, 1624, 1876, 1563, 2489, 1215, 2055, 2043, 2273, 1329, 1659, 2141, 1698, 2134, 2180, 2481, 2263, 1402, 1536, 1285, 2249, 2317, 2462, 1618, 1546, 2456, 2224, 2332, 1808, 1991, 1516, 1827, 2301, 1711, 1869, 1698, 1642, 1932, 1298, 1420, 1860, 1279, 2204, 1798, 1903, 1516, 1483},
                    {1297, 1533, 1613, 1168, 2474, 1849, 2405, 1416, 2339, 1830, 2479, 0, 2252, 1918, 2380, 1330, 1676, 1317, 1395, 1150, 1193, 1174, 2359, 2382, 1821, 1111, 1739, 1388, 1143, 2021, 2272, 2466, 1660, 1490, 1735, 1504, 1741, 1705, 1997, 1938, 2365, 2194, 2307, 1469, 1886, 2329, 1745, 1522, 1615, 1657, 1399, 1243, 2227, 2150, 2256, 1891, 1848, 2216, 2123, 1794},
                    {2125, 2495, 1704, 2136, 1999, 1927, 2449, 1200, 1366, 1334, 2315, 2252, 0, 2147, 1204, 2055, 1830, 1877, 1332, 2290, 1301, 2252, 1166, 2310, 1447, 2353, 2450, 1761, 2152, 1913, 1284, 2271, 2091, 1354, 2082, 2366, 1650, 1548, 2160, 1334, 1665, 1265, 1671, 1891, 1579, 2498, 2276, 1528, 1725, 1696, 1148, 2266, 1550, 2332, 1909, 2070, 2024, 1451, 2320, 1792},
                    {2350, 2119, 2459, 2103, 1728, 2375, 1594, 2471, 2106, 1980, 2366, 1918, 2147, 0, 1688, 2490, 1555, 1265, 2496, 2169, 1275, 1771, 1383, 2079, 1734, 1813, 2414, 1779, 1430, 1118, 2179, 2210, 1562, 1279, 2202, 1953, 2150, 2002, 1687, 1984, 2395, 2241, 2305, 1409, 1138, 2140, 1360, 1197, 2217, 2267, 1815, 1370, 1584, 2004, 2293, 2010, 1741, 1841, 1325, 1578},
                    {2303, 1966, 1770, 1530, 1325, 1860, 2259, 1614, 2238, 2220, 1624, 2380, 1204, 1688, 0, 1627, 1121, 1757, 1789, 1961, 2497, 2323, 2181, 1127, 2359, 1643, 2134, 1508, 1395, 2070, 1711, 2299, 2002, 1781, 1469, 1771, 2353, 1963, 1398, 1989, 1274, 2176, 1303, 2025, 2030, 1705, 2219, 1569, 1431, 1445, 2320, 1527, 1113, 1747, 1751, 2002, 2229, 2087, 2064, 1796},
                    {1255, 1480, 1908, 1120, 2125, 2291, 1293, 1493, 1235, 1532, 1876, 1330, 2055, 2490, 1627, 0, 1504, 1779, 2136, 2087, 1286, 2250, 1576, 1333, 1447, 2488, 2339, 1535, 2044, 2213, 1499, 1549, 1729, 1352, 1305, 1196, 1766, 1848, 1679, 1958, 2227, 1542, 1108, 2228, 1303, 1686, 1323, 1926, 1589, 1477, 1408, 1330, 2276, 1164, 1105, 1123, 2490, 2466, 1668, 2073},
                    {1251, 1896, 1941, 1386, 1113, 2176, 1347, 2452, 2308, 1321, 1563, 1676, 1830, 1555, 1121, 1504, 0, 2276, 1760, 2043, 1614, 1932, 1173, 1840, 2185, 1968, 1330, 1662, 1427, 1139, 1816, 1183, 2447, 2146, 2005, 2060, 2479, 2213, 1486, 2204, 1193, 1575, 1774, 1507, 1800, 1564, 1117, 1694, 2175, 2015, 1452, 1673, 2144, 1493, 1276, 1653, 1595, 1833, 1550, 1145},
                    {1193, 1969, 2107, 1849, 2109, 2073, 1659, 2294, 1448, 1366, 2489, 1317, 1877, 1265, 1757, 1779, 2276, 0, 1570, 2373, 1517, 2140, 2009, 1753, 1208, 1181, 1563, 1548, 1563, 1383, 1895, 1116, 2317, 1756, 1506, 1539, 2453, 1508, 2318, 2440, 2213, 1674, 1535, 2311, 1729, 2227, 1814, 2319, 2337, 2348, 2367, 1597, 1449, 2464, 1133, 1898, 1601, 2395, 2030, 1406},
                    {1983, 2283, 1976, 2207, 2207, 1882, 2287, 1245, 2065, 2362, 1215, 1395, 1332, 2496, 1789, 2136, 1760, 1570, 0, 1993, 2469, 1577, 1871, 1629, 1294, 2187, 1818, 1966, 1434, 1416, 1588, 1952, 1130, 1231, 1766, 1224, 2175, 1999, 2427, 2406, 1123, 1877, 1336, 2424, 2334, 1712, 1991, 2247, 1914, 1699, 1367, 2498, 1667, 2441, 2259, 1880, 1814, 1560, 1229, 2244},
                    {2301, 1123, 1982, 1824, 1337, 1756, 1382, 1368, 2442, 2406, 2055, 1150, 2290, 2169, 1961, 2087, 2043, 2373, 1993, 0, 2461, 1195, 1663, 2073, 1125, 2019, 1742, 1508, 1260, 1121, 2172, 2169, 2274, 1426, 1536, 1518, 1627, 1156, 1324, 2484, 1797, 2488, 2083, 1699, 2485, 2285, 2301, 2213, 1416, 1660, 2008, 1923, 1185, 2059, 1997, 2147, 1958, 1985, 1148, 1291},
                    {1363, 1824, 1685, 1958, 2146, 1349, 1423, 1470, 2403, 1647, 2043, 1193, 1301, 1275, 2497, 1286, 1614, 1517, 2469, 2461, 0, 2359, 2448, 1180, 2288, 2132, 1488, 1531, 2303, 1106, 2351, 1607, 2148, 1466, 2391, 1735, 2361, 1125, 2160, 1600, 2422, 1256, 2237, 1264, 1575, 1952, 1379, 2017, 1603, 2275, 1698, 2258, 1240, 2123, 1328, 1368, 2116, 2103, 2419, 2388},
                    {2451, 2495, 2247, 1479, 1943, 1847, 2188, 2370, 2193, 1933, 2273, 1174, 2252, 1771, 2323, 2250, 1932, 2140, 1577, 1195, 2359, 0, 2303, 2407, 1893, 2105, 2300, 2244, 1381, 1597, 1110, 1249, 2375, 1186, 2351, 2266, 2061, 2103, 2260, 2184, 1758, 2007, 2172, 1767, 1605, 1622, 1375, 1644, 1126, 2004, 1844, 1389, 1153, 1663, 2066, 2391, 2008, 2481, 1728, 1270},
                    {1152, 2400, 2016, 1896, 2457, 1596, 1494, 2243, 2204, 1569, 1329, 2359, 1166, 1383, 2181, 1576, 1173, 2009, 1871, 1663, 2448, 2303, 0, 1357, 1963, 1392, 1845, 1421, 2284, 2149, 2218, 1665, 1131, 2294, 1373, 1659, 1613, 2291, 2109, 1349, 1635, 1188, 1895, 1292, 2243, 2361, 2036, 2076, 1297, 1564, 2293, 1862, 1849, 1875, 2342, 1693, 1908, 2185, 1716, 1742},
                    {1262, 1322, 1979, 2132, 1396, 2012, 2378, 2037, 1109, 1740, 1659, 2382, 2310, 2079, 1127, 1333, 1840, 1753, 1629, 2073, 1180, 2407, 1357, 0, 2445, 1516, 2004, 2220, 1999, 1321, 2250, 1475, 1365, 1723, 1762, 2309, 2173, 1467, 1335, 1601, 1202, 2402, 1278, 1817, 1516, 1745, 1566, 1218, 1670, 1793, 2086, 1703, 2164, 2015, 1154, 1294, 1951, 1215, 1972, 1796},
                    {2252, 2153, 1717, 1191, 1448, 2092, 2020, 2457, 2465, 1125, 2141, 1821, 1447, 1734, 2359, 1447, 2185, 1208, 1294, 1125, 2288, 1893, 1963, 2445, 0, 2103, 2481, 2371, 2204, 2172, 1393, 2123, 1554, 1696, 2000, 1912, 1854, 1756, 1689, 1928, 2332, 2318, 1934, 2381, 2297, 2308, 1520, 1651, 1512, 2039, 1124, 2396, 2418, 1831, 2331, 1117, 1115, 2167, 1307, 2245},
                    {1381, 2453, 1548, 2200, 1205, 1969, 1978, 1230, 1856, 1765, 1698, 1111, 2353, 1813, 1643, 2488, 1968, 1181, 2187, 2019, 2132, 2105, 1392, 1516, 2103, 0, 1209, 2197, 1513, 1602, 1112, 1416, 2416, 1831, 1721, 1134, 2450, 1670, 1945, 2153, 2077, 2198, 1861, 1134, 1225, 1673, 1476, 2058, 1534, 1933, 1172, 1904, 1547, 1277, 2342, 1451, 2113, 2203, 1454, 1653},
                    {2387, 1616, 1332, 2139, 1291, 1911, 1143, 1383, 1169, 2036, 2134, 1739, 2450, 2414, 2134, 2339, 1330, 1563, 1818, 1742, 1488, 2300, 1845, 2004, 2481, 1209, 0, 1398, 2308, 1475, 2474, 2125, 1368, 2437, 1308, 1666, 1332, 2124, 2123, 1971, 2294, 2228, 1257, 2434, 2180, 1366, 1775, 1368, 1850, 1931, 1928, 1764, 1998, 1513, 2000, 1248, 2426, 1464, 1424, 1476},
                    {1782, 2360, 2497, 2282, 1756, 2187, 2341, 1283, 1608, 1681, 2180, 1388, 1761, 1779, 1508, 1535, 1662, 1548, 1966, 1508, 1531, 2244, 1421, 2220, 2371, 2197, 1398, 0, 1549, 1598, 1793, 2188, 2030, 1630, 1914, 1612, 2174, 1184, 1991, 2468, 2192, 1946, 1856, 1879, 2456, 1707, 2220, 1669, 2118, 2328, 1832, 2425, 2334, 2144, 2377, 2004, 1660, 2338, 1657, 2208},
                    {1625, 1771, 1341, 2108, 1818, 2301, 1350, 1455, 1280, 1968, 2481, 1143, 2152, 1430, 1395, 2044, 1427, 1563, 1434, 1260, 2303, 1381, 2284, 1999, 2204, 1513, 2308, 1549, 0, 1993, 2094, 1184, 1137, 1445, 1673, 1347, 2285, 1336, 1906, 1826, 2325, 1169, 1921, 1976, 2059, 1793, 1767, 2076, 1587, 2421, 1129, 1503, 1103, 2135, 1790, 1569, 1346, 1136, 1280, 2437},
                    {2156, 1894, 2426, 1201, 1800, 1557, 1994, 1469, 1453, 1387, 2263, 2021, 1913, 1118, 2070, 2213, 1139, 1383, 1416, 1121, 1106, 1597, 2149, 1321, 2172, 1602, 1475, 1598, 1993, 0, 1422, 1996, 2471, 1925, 2064, 2473, 1993, 1843, 1345, 1285, 1238, 2006, 2264, 1852, 1815, 1901, 1995, 1267, 1862, 1171, 2181, 1924, 1193, 1503, 1241, 2206, 1336, 2454, 1516, 1688},
                    {1663, 2231, 1154, 1619, 2433, 1319, 1800, 2172, 1341, 1517, 1402, 2272, 1284, 2179, 1711, 1499, 1816, 1895, 1588, 2172, 2351, 1110, 2218, 2250, 1393, 1112, 2474, 1793, 2094, 1422, 0, 1510, 2080, 1769, 2393, 1413, 2108, 1982, 1399, 1778, 1248, 1811, 2091, 1975, 1857, 1915, 1901, 1655, 1555, 1242, 1930, 1708, 2061, 1215, 2142, 2364, 1637, 1940, 1463, 1418},
                    {1891, 1673, 1886, 2340, 1124, 2017, 1122, 1952, 1555, 1191, 1536, 2466, 2271, 2210, 2299, 1549, 1183, 1116, 1952, 2169, 1607, 1249, 1665, 1475, 2123, 1416, 2125, 2188, 1184, 1996, 1510, 0, 1507, 2288, 1912, 1113, 2045, 1717, 2198, 2308, 1287, 2277, 1971, 1128, 2188, 2200, 1908, 1451, 1149, 1344, 1969, 1901, 2357, 1573, 1193, 1988, 1783, 1229, 1432, 2399},
                    {2389, 1440, 2292, 1100, 1493, 1875, 2168, 2412, 1536, 2329, 1285, 1660, 2091, 1562, 2002, 1729, 2447, 2317, 1130, 2274, 2148, 2375, 1131, 1365, 1554, 2416, 1368, 2030, 1137, 2471, 2080, 1507, 0, 2012, 1203, 1502, 1944, 1389, 2352, 2436, 1188, 2137, 2079, 2478, 1911, 2307, 1714, 1920, 1552, 1364, 2051, 2358, 2468, 2120, 1764, 1556, 1766, 1448, 1312, 2430},
                    {1968, 1232, 2173, 1600, 2161, 1552, 1258, 2075, 2023, 2393, 2249, 1490, 1354, 1279, 1781, 1352, 2146, 1756, 1231, 1426, 1466, 1186, 2294, 1723, 1696, 1831, 2437, 1630, 1445, 1925, 1769, 2288, 2012, 0, 2276, 2243, 2418, 2349, 2237, 2393, 1251, 1175, 1198, 1830, 1962, 1721, 2132, 2167, 1339, 1972, 2123, 1885, 2466, 2434, 1317, 1235, 2192, 1483, 2495, 1573},
                    {2002, 2431, 1607, 1874, 1404, 2159, 1438, 1107, 1470, 1834, 2317, 1735, 2082, 2202, 1469, 1305, 2005, 1506, 1766, 1536, 2391, 2351, 1373, 1762, 2000, 1721, 1308, 1914, 1673, 2064, 2393, 1912, 1203, 2276, 0, 1100, 1496, 1125, 1663, 1702, 1643, 2350, 2263, 2068, 1281, 1786, 1744, 1519, 1221, 2423, 2228, 2048, 1419, 1811, 1397, 2196, 1349, 1676, 2320, 2279},
                    {2369, 2009, 2467, 1138, 1136, 1452, 2240, 2276, 2421, 2026, 2462, 1504, 2366, 1953, 1771, 1196, 2060, 1539, 1224, 1518, 1735, 2266, 1659, 2309, 1912, 1134, 1666, 1612, 1347, 2473, 1413, 1113, 1502, 2243, 1100, 0, 1829, 1987, 1670, 1372, 1976, 1695, 2488, 1171, 1531, 2206, 1247, 1579, 2085, 2487, 2286, 2438, 2369, 1114, 2420, 1668, 1102, 1912, 1650, 1184},
                    {1522, 1969, 1119, 1201, 2321, 1552, 1548, 1314, 1927, 2356, 1618, 1741, 1650, 2150, 2353, 1766, 2479, 2453, 2175, 1627, 2361, 2061, 1613, 2173, 1854, 2450, 1332, 2174, 2285, 1993, 2108, 2045, 1944, 2418, 1496, 1829, 0, 2133, 1135, 1453, 1159, 2454, 1254, 2200, 1502, 2222, 1415, 2489, 1539, 2025, 1330, 1870, 1460, 2313, 2302, 2484, 2156, 1169, 1698, 2276},
                    {1792, 1460, 1171, 2489, 2085, 2253, 1207, 2452, 2245, 2103, 1546, 1705, 1548, 2002, 1963, 1848, 2213, 1508, 1999, 1156, 1125, 2103, 2291, 1467, 1756, 1670, 2124, 1184, 1336, 1843, 1982, 1717, 1389, 2349, 1125, 1987, 2133, 0, 2044, 1184, 2410, 1650, 2014, 2109, 2469, 2147, 1804, 1467, 2355, 1275, 1919, 1281, 1580, 2297, 1572, 1769, 1628, 1183, 1360, 1578},
                    {2094, 2222, 2359, 1625, 2163, 2276, 1529, 2036, 2488, 1533, 2456, 1997, 2160, 1687, 1398, 1679, 1486, 2318, 2427, 1324, 2160, 2260, 2109, 1335, 1689, 1945, 2123, 1991, 1906, 1345, 1399, 2198, 2352, 2237, 1663, 1670, 1135, 2044, 0, 1274, 1385, 1549, 1549, 1168, 1585, 2333, 1241, 2124, 1573, 1602, 2195, 1189, 1510, 1736, 2101, 1106, 2029, 2098, 1129, 1220},
                    {1430, 2348, 2156, 2149, 1697, 1249, 1443, 1274, 2231, 1710, 2224, 1938, 1334, 1984, 1989, 1958, 2204, 2440, 2406, 2484, 1600, 2184, 1349, 1601, 1928, 2153, 1971, 2468, 1826, 1285, 1778, 2308, 2436, 2393, 1702, 1372, 1453, 1184, 1274, 0, 1964, 2313, 2363, 1307, 1199, 1523, 1793, 1318, 1565, 1344, 1183, 2028, 1823, 1360, 1283, 2280, 2459, 1321, 2002, 2034},
                    {1778, 2294, 2301, 1995, 2355, 2240, 1937, 1670, 1651, 2130, 2332, 2365, 1665, 2395, 1274, 2227, 1193, 2213, 1123, 1797, 2422, 1758, 1635, 1202, 2332, 2077, 2294, 2192, 2325, 1238, 1248, 1287, 1188, 1251, 1643, 1976, 1159, 2410, 1385, 1964, 0, 1549, 1308, 1853, 1127, 2139, 1358, 1332, 1746, 2388, 1236, 2324, 1260, 1244, 1320, 2168, 1199, 2086, 2193, 2282},
                    {1836, 1128, 1968, 1265, 1631, 1200, 1569, 2408, 2000, 2432, 1808, 2194, 1265, 2241, 2176, 1542, 1575, 1674, 1877, 2488, 1256, 2007, 1188, 2402, 2318, 2198, 2228, 1946, 1169, 2006, 1811, 2277, 2137, 1175, 2350, 1695, 2454, 1650, 1549, 2313, 1549, 0, 2161, 2495, 1399, 1484, 1492, 1563, 2131, 1552, 1810, 1966, 1108, 1568, 1486, 1977, 2038, 1354, 1829, 1158},
                    {2334, 1145, 2203, 1645, 1608, 2145, 2251, 1232, 2072, 1417, 1991, 2307, 1671, 2305, 1303, 1108, 1774, 1535, 1336, 2083, 2237, 2172, 1895, 1278, 1934, 1861, 1257, 1856, 1921, 2264, 2091, 1971, 2079, 1198, 2263, 2488, 1254, 2014, 1549, 2363, 1308, 2161, 0, 1541, 2052, 2499, 2002, 1928, 1160, 1873, 1607, 1897, 2004, 1908, 1299, 2472, 2473, 1410, 1552, 1471},
                    {2439, 1579, 1867, 1174, 1125, 1858, 1159, 1713, 2391, 1757, 1516, 1469, 1891, 1409, 2025, 2228, 1507, 2311, 2424, 1699, 1264, 1767, 1292, 1817, 2381, 1134, 2434, 1879, 1976, 1852, 1975, 1128, 2478, 1830, 2068, 1171, 2200, 2109, 1168, 1307, 1853, 2495, 1541, 0, 1957, 1406, 1873, 1417, 1887, 2323, 1701, 1291, 2303, 1748, 1779, 1780, 1519, 1785, 1523, 1401},
                    {2404, 2203, 2042, 1474, 1524, 1360, 1880, 2495, 2344, 1335, 1827, 1886, 1579, 1138, 2030, 1303, 1800, 1729, 2334, 2485, 1575, 1605, 2243, 1516, 2297, 1225, 2180, 2456, 2059, 1815, 1857, 2188, 1911, 1962, 1281, 1531, 1502, 2469, 1585, 1199, 1127, 1399, 2052, 1957, 0, 1954, 1271, 1517, 2481, 2232, 1535, 1712, 1799, 2203, 1502, 1136, 1734, 1913, 1756, 1709},
                    {1823, 1211, 1860, 1688, 1450, 1317, 1715, 2280, 2144, 2032, 2301, 2329, 2498, 2140, 1705, 1686, 1564, 2227, 1712, 2285, 1952, 1622, 2361, 1745, 2308, 1673, 1366, 1707, 1793, 1901, 1915, 2200, 2307, 1721, 1786, 2206, 2222, 2147, 2333, 1523, 2139, 1484, 2499, 1406, 1954, 0, 1903, 2342, 1645, 2493, 1789, 2100, 1862, 2285, 1722, 1986, 1706, 1409, 2346, 1871},
                    {1375, 2417, 1607, 2352, 1309, 2037, 1390, 1661, 1108, 2328, 1711, 1745, 2276, 1360, 2219, 1323, 1117, 1814, 1991, 2301, 1379, 1375, 2036, 1566, 1520, 1476, 1775, 2220, 1767, 1995, 1901, 1908, 1714, 2132, 1744, 1247, 1415, 1804, 1241, 1793, 1358, 1492, 2002, 1873, 1271, 1903, 0, 1514, 1813, 1965, 1330, 2186, 2319, 1651, 1120, 2168, 1588, 2183, 1248, 2472},
                    {1365, 1856, 2189, 2149, 1438, 1688, 1184, 1913, 1303, 1801, 1869, 1522, 1528, 1197, 1569, 1926, 1694, 2319, 2247, 2213, 2017, 1644, 2076, 1218, 1651, 2058, 1368, 1669, 2076, 1267, 1655, 1451, 1920, 2167, 1519, 1579, 2489, 1467, 2124, 1318, 1332, 1563, 1928, 1417, 1517, 2342, 1514, 0, 1803, 1462, 2446, 1411, 1930, 2353, 2370, 2206, 1795, 1885, 1604, 2463},
                    {1480, 1318, 2174, 1302, 1294, 1343, 2491, 1118, 2397, 2382, 1698, 1615, 1725, 2217, 1431, 1589, 2175, 2337, 1914, 1416, 1603, 1126, 1297, 1670, 1512, 1534, 1850, 2118, 1587, 1862, 1555, 1149, 1552, 1339, 1221, 2085, 1539, 2355, 1573, 1565, 1746, 2131, 1160, 1887, 2481, 1645, 1813, 1803, 0, 1474, 2332, 2231, 2258, 2454, 1548, 2351, 1977, 1137, 1366, 1688},
                    {1807, 1157, 2174, 1156, 1153, 1377, 1623, 2130, 2473, 2207, 1642, 1657, 1696, 2267, 1445, 1477, 2015, 2348, 1699, 1660, 2275, 2004, 1564, 1793, 2039, 1933, 1931, 2328, 2421, 1171, 1242, 1344, 1364, 1972, 2423, 2487, 2025, 1275, 1602, 1344, 2388, 1552, 1873, 2323, 2232, 2493, 1965, 1462, 1474, 0, 1210, 1638, 2281, 1981, 1594, 2438, 1549, 1841, 1543, 1847},
                    {2484, 1220, 1238, 1297, 2028, 2392, 1239, 1711, 1186, 1169, 1932, 1399, 1148, 1815, 2320, 1408, 1452, 2367, 1367, 2008, 1698, 1844, 2293, 2086, 1124, 1172, 1928, 1832, 1129, 2181, 1930, 1969, 2051, 2123, 2228, 2286, 1330, 1919, 2195, 1183, 1236, 1810, 1607, 1701, 1535, 1789, 1330, 2446, 2332, 1210, 0, 2155, 1333, 1124, 1546, 1385, 1391, 1749, 2383, 1900},
                    {2440, 1846, 2356, 1382, 1685, 1501, 2492, 1288, 1810, 1658, 1298, 1243, 2266, 1370, 1527, 1330, 1673, 1597, 2498, 1923, 2258, 1389, 1862, 1703, 2396, 1904, 1764, 2425, 1503, 1924, 1708, 1901, 2358, 1885, 2048, 2438, 1870, 1281, 1189, 2028, 2324, 1966, 1897, 1291, 1712, 2100, 2186, 1411, 2231, 1638, 2155, 0, 2211, 1578, 1407, 1550, 2016, 1233, 1718, 1559},
                    {2310, 1980, 2074, 1734, 2350, 1226, 2073, 1694, 1177, 1112, 1420, 2227, 1550, 1584, 1113, 2276, 2144, 1449, 1667, 1185, 1240, 1153, 1849, 2164, 2418, 1547, 1998, 2334, 1103, 1193, 2061, 2357, 2468, 2466, 1419, 2369, 1460, 1580, 1510, 1823, 1260, 1108, 2004, 2303, 1799, 1862, 2319, 1930, 2258, 2281, 1333, 2211, 0, 1890, 1300, 2203, 1135, 1624, 2393, 1637},
                    {1641, 1392, 1939, 2480, 2446, 1260, 1598, 1663, 1129, 2282, 1860, 2150, 2332, 2004, 1747, 1164, 1493, 2464, 2441, 2059, 2123, 1663, 1875, 2015, 1831, 1277, 1513, 2144, 2135, 1503, 1215, 1573, 2120, 2434, 1811, 1114, 2313, 2297, 1736, 1360, 1244, 1568, 1908, 1748, 2203, 2285, 1651, 2353, 2454, 1981, 1124, 1578, 1890, 0, 2073, 1434, 2224, 1932, 1524, 1848},
                    {1289, 1943, 2389, 2344, 1350, 1413, 1283, 1264, 1644, 1767, 1279, 2256, 1909, 2293, 1751, 1105, 1276, 1133, 2259, 1997, 1328, 2066, 2342, 1154, 2331, 2342, 2000, 2377, 1790, 1241, 2142, 1193, 1764, 1317, 1397, 2420, 2302, 1572, 2101, 1283, 1320, 1486, 1299, 1779, 1502, 1722, 1120, 2370, 1548, 1594, 1546, 1407, 1300, 2073, 0, 1831, 2337, 1939, 2393, 1241},
                    {1886, 1783, 2436, 1981, 2243, 1753, 1370, 2140, 1291, 1645, 2204, 1891, 2070, 2010, 2002, 1123, 1653, 1898, 1880, 2147, 1368, 2391, 1693, 1294, 1117, 1451, 1248, 2004, 1569, 2206, 2364, 1988, 1556, 1235, 2196, 1668, 2484, 1769, 1106, 2280, 2168, 1977, 2472, 1780, 1136, 1986, 2168, 2206, 2351, 2438, 1385, 1550, 2203, 1434, 1831, 0, 1314, 1932, 2375, 2430},
                    {1775, 1699, 1798, 1260, 1940, 2206, 1274, 1866, 2203, 1779, 1798, 1848, 2024, 1741, 2229, 2490, 1595, 1601, 1814, 1958, 2116, 2008, 1908, 1951, 1115, 2113, 2426, 1660, 1346, 1336, 1637, 1783, 1766, 2192, 1349, 1102, 2156, 1628, 2029, 2459, 1199, 2038, 2473, 1519, 1734, 1706, 1588, 1795, 1977, 1549, 1391, 2016, 1135, 2224, 2337, 1314, 0, 1886, 1118, 1675},
                    {1518, 1399, 1743, 1107, 1171, 1642, 1181, 2382, 1641, 2351, 1903, 2216, 1451, 1841, 2087, 2466, 1833, 2395, 1560, 1985, 2103, 2481, 2185, 1215, 2167, 2203, 1464, 2338, 1136, 2454, 1940, 1229, 1448, 1483, 1676, 1912, 1169, 1183, 2098, 1321, 2086, 1354, 1410, 1785, 1913, 1409, 2183, 1885, 1137, 1841, 1749, 1233, 1624, 1932, 1939, 1932, 1886, 0, 1764, 1905},
                    {1873, 2351, 1618, 2074, 2091, 1937, 1172, 2076, 1517, 1405, 1516, 2123, 2320, 1325, 2064, 1668, 1550, 2030, 1229, 1148, 2419, 1728, 1716, 1972, 1307, 1454, 1424, 1657, 1280, 1516, 1463, 1432, 1312, 2495, 2320, 1650, 1698, 1360, 1129, 2002, 2193, 1829, 1552, 1523, 1756, 2346, 1248, 1604, 1366, 1543, 2383, 1718, 2393, 1524, 2393, 2375, 1118, 1764, 0, 1755},
                    {1159, 1603, 1193, 1386, 1532, 1136, 2132, 1651, 1346, 2442, 1483, 1794, 1792, 1578, 1796, 2073, 1145, 1406, 2244, 1291, 2388, 1270, 1742, 1796, 2245, 1653, 1476, 2208, 2437, 1688, 1418, 2399, 2430, 1573, 2279, 1184, 2276, 1578, 1220, 2034, 2282, 1158, 1471, 1401, 1709, 1871, 2472, 2463, 1688, 1847, 1900, 1559, 1637, 1848, 1241, 2430, 1675, 1905, 1755, 0}

            };
    private static Airport a1 =new Airport("ATL", 0,13, 23, m_distance/Airplane.speed, 15);
    private static Airport a2 =new Airport("SFO", 1,11, 25, m_distance/Airplane.speed, 12);
    private static Airport a3 =new Airport("JFK", 2,10, 23, m_distance/Airplane.speed, 12);
    private static Airport a4 =new Airport("ORD", 3,14, 20, m_distance/Airplane.speed, 12);
    private static Airport a5 =new Airport("AUS", 4,15, 20, m_distance/Airplane.speed, 11);
    private static Airport a6 =new Airport("PHX", 5,12, 25, m_distance/Airplane.speed, 14);
    private static Airport a7 =new Airport("LAX", 6,11, 21, m_distance/Airplane.speed, 15);
    private static Airport a8 =new Airport("DFW", 7,13, 24, m_distance/Airplane.speed, 12);
    private static Airport a9 =new Airport("DEN", 8,13, 21, m_distance/Airplane.speed, 11);
    private static Airport a10 =new Airport("CLT", 9,11, 23, m_distance/Airplane.speed, 10);
    private static Airport a11 =new Airport("LAS", 10,12, 24, m_distance/Airplane.speed, 12);
    private static Airport a12 =new Airport("IAH", 11,15, 24, m_distance/Airplane.speed, 11);
    private static Airport a13 =new Airport("MCO", 12,13, 23, m_distance/Airplane.speed, 15);
    private static Airport a14 =new Airport("EWR", 13,11, 25, m_distance/Airplane.speed, 13);
    private static Airport a15 =new Airport("MSP", 14,14, 22, m_distance/Airplane.speed, 15);
    private static Airport a16 =new Airport("MIA", 15,13, 22, m_distance/Airplane.speed, 12);
    private static Airport a17 =new Airport("DTW", 16,10, 20, m_distance/Airplane.speed, 10);
    private static Airport a18 =new Airport("BOS", 17,12, 22, m_distance/Airplane.speed, 11);
    private static Airport a19 =new Airport("SEA", 18,13, 20, m_distance/Airplane.speed, 13);
    private static Airport a20 =new Airport("LGA", 19,13, 22, m_distance/Airplane.speed, 10);
    private static Airport a21 =new Airport("PHL", 20,14, 20, m_distance/Airplane.speed, 13);
    private static Airport a22 =new Airport("TPA", 21,14, 23, m_distance/Airplane.speed, 15);
    private static Airport a23 =new Airport("IAD", 22,15, 21, m_distance/Airplane.speed, 10);
    private static Airport a24 =new Airport("BWI", 23,15, 22, m_distance/Airplane.speed, 12);
    private static Airport a25 =new Airport("FLL", 24,11, 22, m_distance/Airplane.speed, 15);
    private static Airport a26 =new Airport("MDW", 25,11, 25, m_distance/Airplane.speed, 10);
    private static Airport a27 =new Airport("SLC", 26,13, 22, m_distance/Airplane.speed, 12);
    private static Airport a28 =new Airport("HNL", 27,14, 23, m_distance/Airplane.speed, 14);
    private static Airport a29 =new Airport("SAN", 28,10, 25, m_distance/Airplane.speed, 12);
    private static Airport a30 =new Airport("DCA", 29,12, 21, m_distance/Airplane.speed, 11);
    private static Airport a31 =new Airport("PDX", 30,11, 21, m_distance/Airplane.speed, 10);
    private static Airport a32 =new Airport("RSW", 31,15, 21, m_distance/Airplane.speed, 14);
    private static Airport a33 =new Airport("STL", 32,14, 23, m_distance/Airplane.speed, 12);
    private static Airport a34 =new Airport("MEM", 33,14, 21, m_distance/Airplane.speed, 11);
    private static Airport a35 =new Airport("MCI", 34,14, 21, m_distance/Airplane.speed, 13);
    private static Airport a36 =new Airport("OAK", 35,11, 21, m_distance/Airplane.speed, 11);
    private static Airport a37 =new Airport("MSY", 36,14, 21, m_distance/Airplane.speed, 13);
    private static Airport a38 =new Airport("SNA", 37,15, 25, m_distance/Airplane.speed, 11);
    private static Airport a39 =new Airport("RDU", 38,13, 22, m_distance/Airplane.speed, 10);
    private static Airport a40 =new Airport("BNA", 39,14, 21, m_distance/Airplane.speed, 11);
    private static Airport a41 =new Airport("CLE", 40,15, 23, m_distance/Airplane.speed, 14);
    private static Airport a42 =new Airport("HOU", 41,11, 23, m_distance/Airplane.speed, 14);
    private static Airport a43 =new Airport("SJC", 42,10, 25, m_distance/Airplane.speed, 10);
    private static Airport a44 =new Airport("SMF", 43,10, 24, m_distance/Airplane.speed, 10);
    private static Airport a45 =new Airport("SJU", 44,11, 22, m_distance/Airplane.speed, 10);
    private static Airport a46 =new Airport("AUS", 45,11, 20, m_distance/Airplane.speed, 11);
    private static Airport a47 =new Airport("SAT", 46,10, 20, m_distance/Airplane.speed, 11);
    private static Airport a48 =new Airport("PIT", 47,15, 25, m_distance/Airplane.speed, 13);
    private static Airport a49 =new Airport("DAL", 48,11, 21, m_distance/Airplane.speed, 10);
    private static Airport a50 =new Airport("IND", 49,14, 22, m_distance/Airplane.speed, 14);
    private static Airport a51 =new Airport("MKE", 50,11, 25, m_distance/Airplane.speed, 11);
    private static Airport a52 =new Airport("CMH", 51,13, 21, m_distance/Airplane.speed, 10);
    private static Airport a53 =new Airport("OGG", 52,11, 23, m_distance/Airplane.speed, 13);
    private static Airport a54 =new Airport("PBI", 53,14, 23, m_distance/Airplane.speed, 14);
    private static Airport a55 =new Airport("CVG", 54,11, 22, m_distance/Airplane.speed, 10);
    private static Airport a56 =new Airport("TUS", 55,11, 20, m_distance/Airplane.speed, 15);
    private static Airport a57 =new Airport("BDL", 56,13, 25, m_distance/Airplane.speed, 14);
    private static Airport a58 =new Airport("BUF", 57,12, 24, m_distance/Airplane.speed, 11);
    private static Airport a59 =new Airport("KAX", 58,14, 21, m_distance/Airplane.speed, 12);
    private static Airport a60 =new Airport("RNO", 59,11, 24, m_distance/Airplane.speed, 12);

    public static Airport airpotarray[] = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31,a32,a33,a34,a35,a36,a37,a38,a39,a40,a41,a42,a43,a44,a45,a46,a47,a48,a49,a50,a51,a52,a53,a54,a55,a56,a57,a58,a59,a60};








    // add fuel and bad weather
    public static String []CONDITION = {"normal", "snow", "storm", "lightening", "smoke fog", "heavy rain"};
    public static double[] DELAYTIME = new double[]{0,45,50,35,20,35};
    private int m_weather;
    private int[] weatherList;
    private double m_totalFuelDelay=0;
    private double m_totalWeatherDelay=0;
    // private double m_usedFuel;

    public Airport(String name,int index, double runwayTimeToLand, double requiredTimeOnGround, 
        double flightTime, double runwayTimeToTakeOff) {
        m_airportName = name;
        m_inTheAir =  0;
        m_onTheGround = 0;
        m_freeToLand = true;
        m_freeToTakeoff=true;
        m_runwayTimeToLand = runwayTimeToLand;
        m_requiredTimeOnGround = requiredTimeOnGround;
        m_flightTime = flightTime;
        m_runwayTimeToTakeOff = runwayTimeToTakeOff;
        m_index=index;
        m_weather = 0;
        get_weatherList();
    }

    public String getName() {
        return m_airportName;
    }

    private void setM_flightTime(int start, int end){
        m_flightTime = DISTANCE[start][end]/Airplane.speed;
    }
    //add fuel and bad weather
    public int[] get_weatherList(){
        int[] r = new int[]{15,3,2,2,2,1};
        int total = 0;
        for(int i = 0; i < r.length; i++)
            total += r[i];
        int[] m = new int[total];
        int indexp = 0;
        for(int i = 0; i < r.length; i++) {
            for(int j = 0; j < r[i]; j++)
                m[indexp++] = i;
        }
        int timePeriodId;
        timePeriodId = (int)(AirportSim.SimulationTime);
        weatherList = new int[timePeriodId];
        for(int i = 0; i < timePeriodId; i++){
            int randWeather;
            randWeather = (int)(Math.random()*total);
            weatherList[i] = m[randWeather];
        }
        return weatherList;
    }
    public void set_weather(){
        int timeId;
        timeId = (int)(Simulator.getCurrentTime());
        m_weather = weatherList[timeId];
    }
    public int get_weather(){
        return m_weather;
    }
    ///end


    public void handle(Event event)  {
        if (AirportSim.multipath==false){

        AirportEvent airEvent = (AirportEvent)event;
        String name = airEvent.get_airplane().getDestination().getName();

        switch(airEvent.getType()) {
            case AirportEvent.PLANE_ARRIVES:
                m_inTheAir++;
                airEvent.get_airplane().set_ArrivalTime(Simulator.getCurrentTime());
                LandingQueue.offer(airEvent);
                if(m_freeToLand) {
                    AirportEvent newEventArrival = LandingQueue.poll();
                    m_freeToLand = false;
                    AirportEvent landedEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, newEventArrival.get_airplane());
                    Simulator.schedule(landedEvent);
                }
                break;

            case AirportEvent.PLANE_LANDED:
                m_inTheAir--;
                m_onTheGround++;
                m_circlingTime = Simulator.getCurrentTime() - airEvent.get_airplane().get_ArrivalTime()-m_runwayTimeToLand;
                m_totalCirclingTime += m_circlingTime;
                m_totalLandingPassenger += airEvent.get_airplane().get_passenger();
                double timenow;
                timenow = Simulator.getCurrentTime();
                double m_requiredByFuel;
                if(airEvent.get_airplane().get_fuel() < Airplane.fuelLimit){
                    m_requiredByFuel = 0;
                    AirportSim.builder.append("------Time: " + String.valueOf((int)(Simulator.getCurrentTime()/60))+":"+String.valueOf((int)(Simulator.getCurrentTime()%60))+"------\n"+airEvent.get_airplane().get_Name() + " NO need refuel \n");
                }else{
                    m_requiredByFuel = (int)(Math.random()*5+20);
                    AirportSim.builder.append("------Time: " + String.valueOf((int)(Simulator.getCurrentTime()/60))+":"+String.valueOf((int)(Simulator.getCurrentTime()%60))+"------\n"+ airEvent.get_airplane().get_Name() + " need refuel \n");
                }
                m_totalFuelDelay += m_requiredByFuel;
                double m_requiredByWeather;
                set_weather();
                if(get_weather()==0) m_requiredByWeather=0;
                else{m_requiredByWeather = DELAYTIME[get_weather()]+(int)(Math.random()*7);}
                resultarray= AirportSim.result.get(m_airportName);


                AirportSim.builder.append( "Arrive at " + m_airportName +
                    " " + " " + CONDITION[get_weather()]+ " weather delay time "+ m_requiredByWeather +"\n");



                m_totalWeatherDelay += m_requiredByWeather;

                String line1 ="passenger number: "+
                        String.valueOf(airEvent.get_airplane().get_passenger())+ " , total Landing passenger number: " +String.valueOf(m_totalLandingPassenger)+"\n" ;
                AirportSim.builder.append(line1);

                String line2 = "circling time: "+
                        String.valueOf((float)Math.round(m_circlingTime * 100) / 100)+ " , total circling time: " +String.valueOf((float)Math.round(m_totalCirclingTime * 100) / 100) +"\n" + "total fuel delay " + String.valueOf((float)Math.round(m_totalFuelDelay * 100) / 100) +
                        " , " + " total weather delay " + String.valueOf((float)Math.round(m_totalWeatherDelay * 100) / 100) + "\n";
                AirportSim.builder.append(line2);
                resultarray.set(2,String.valueOf((int)m_totalWeatherDelay));
                resultarray.set(3,String.valueOf(m_totalLandingPassenger));
                resultarray.set(0,String.valueOf((int)((float)Math.round(m_totalCirclingTime * 100) / 100)));
                resultarray.set(1,String.valueOf((int)((float)Math.round(m_totalFuelDelay * 100) / 100)));

                AirportEvent departureEvent = new AirportEvent(m_requiredTimeOnGround, this, AirportEvent.PLANE_DEPARTS,airEvent.get_airplane());
                Simulator.schedule(departureEvent);

                if(!DepartingQueue.isEmpty() && !LandingQueue.isEmpty()){
                    if(DepartingQueue.element().getTime() < LandingQueue.element().getTime()){
                        AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, DepartingQueue.poll().get_airplane());
                        Simulator.schedule(takeoffEvent);
                    }
                    else {
                        AirportEvent landingEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, LandingQueue.poll().get_airplane());
                        Simulator.schedule(landingEvent);
                    }
                }
                else if(!DepartingQueue.isEmpty() ) {
                    AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, DepartingQueue.poll().get_airplane());
                    Simulator.schedule(takeoffEvent);
                }
                else if(!LandingQueue.isEmpty())
                {
                    AirportEvent landingEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, LandingQueue.poll().get_airplane());
                    Simulator.schedule(landingEvent);
                }
                else {
                    m_freeToLand = true;
                }


                break;

            case AirportEvent.PLANE_DEPARTS:
                DepartingQueue.offer(airEvent);
                if(m_freeToLand){
                    AirportEvent newEventDepart = DepartingQueue.poll();
                    m_freeToLand = false;
                    AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, newEventDepart.get_airplane());
                    Simulator.schedule(takeoffEvent);
                }
                break;

            case AirportEvent.PLANE_TAKEOFF:
                m_onTheGround--;
                Airport nextAirport;
                airEvent.get_airplane().set_passenger();
                int randDestination;
                do{ randDestination = (int)(Math.random()*5) ;}
                    while (randDestination == m_index );
                nextAirport = airpotarray[randDestination];
                setM_flightTime(m_index,randDestination);
                airEvent.get_airplane().set_destination(nextAirport);

                airEvent.get_airplane().set_fuel(m_index, randDestination);

                AirportEvent arrivalEvent = new AirportEvent(this.m_flightTime, nextAirport, AirportEvent.PLANE_ARRIVES, airEvent.get_airplane());
                Simulator.schedule(arrivalEvent);
                m_totalLeavingPassenger += airEvent.get_airplane().get_passenger();
                String line =String.valueOf("------Time: " + String.valueOf((int)(Simulator.getCurrentTime()/60))+":"+String.valueOf((int)(Simulator.getCurrentTime()%60))+"------\n"+airEvent.get_airplane().get_Name()+" Departing from "+ AIRPORT[m_index] + "\n"+" onboard passengers: " + String.valueOf(airEvent.get_airplane().get_passenger())+ " total leaving passengers: " +String.valueOf(m_totalLeavingPassenger)+"\n");
                AirportSim.builder.append(line);
                resultarray= AirportSim.result.get(m_airportName);
                resultarray.set(4,String.valueOf(m_totalLeavingPassenger));



                if(!DepartingQueue.isEmpty() && !LandingQueue.isEmpty()){
                    if(DepartingQueue.element().getTime() < LandingQueue.element().getTime()){
                        AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, DepartingQueue.poll().get_airplane());
                        Simulator.schedule(takeoffEvent);
                    }
                    else {
                        AirportEvent landingEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, LandingQueue.poll().get_airplane());
                        Simulator.schedule(landingEvent);
                    }
                }
                else if(!DepartingQueue.isEmpty() ) {
                    AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, DepartingQueue.poll().get_airplane());
                    Simulator.schedule(takeoffEvent);
                }
                else if(!LandingQueue.isEmpty())
                {
                    AirportEvent landingEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, LandingQueue.poll().get_airplane());
                    Simulator.schedule(landingEvent);
                }
                else {
                    m_freeToLand = true;
                }
                break;
        }
        }

        if (AirportSim.multipath==true){


                AirportEvent airEvent = (AirportEvent)event;
                String name = airEvent.get_airplane().getDestination().getName();
                //m_builder=AirportSim.builder;

                switch(airEvent.getType()) {
                    case AirportEvent.PLANE_ARRIVES:
                        m_inTheAir++;
                        airEvent.get_airplane().set_ArrivalTime(Simulator.getCurrentTime());
                        LandingQueue.offer(airEvent);
                        if(m_freeToLand) {
                            AirportEvent newEventArrival = LandingQueue.poll();
                            m_freeToLand = false;
                            AirportEvent landedEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, newEventArrival.get_airplane());
                            Simulator.schedule(landedEvent);
                        }
                        break;

                    case AirportEvent.PLANE_LANDED:
                        m_inTheAir--;
                        m_onTheGround++;
                        m_circlingTime = Simulator.getCurrentTime() - airEvent.get_airplane().get_ArrivalTime()-m_runwayTimeToLand;
                        m_totalCirclingTime += m_circlingTime;
                        m_totalLandingPassenger += airEvent.get_airplane().get_passenger();
                        double timenow;
                        timenow = Simulator.getCurrentTime();
                        // add fuel and bad weather
                        double m_requiredByFuel;
                        if(airEvent.get_airplane().get_fuel() < Airplane.fuelLimit){
                            m_requiredByFuel = 0;
                            AirportSim.builder.append("------Time: " + String.valueOf((int)(Simulator.getCurrentTime()/60))+":"+String.valueOf((int)(Simulator.getCurrentTime()%60))+"------\n"+airEvent.get_airplane().get_Name() + " NO need refuel");
                        }else{
                            m_requiredByFuel = (int)(Math.random()*5+20);
                            AirportSim.builder.append("------Time: " + String.valueOf((int)(Simulator.getCurrentTime()/60))+":"+String.valueOf((int)(Simulator.getCurrentTime()%60))+"------\n"+ airEvent.get_airplane().get_Name() + " need refuel ");
                        }
                        m_totalFuelDelay += m_requiredByFuel;
                        double m_requiredByWeather;
                        set_weather();
                        if(get_weather()==0) m_requiredByWeather=0;
                        else{m_requiredByWeather = DELAYTIME[get_weather()]+(int)(Math.random()*7);}

                        resultarray= AirportSim.result.get(m_airportName);
                        AirportSim.builder.append( "Arrive at " + m_airportName +
                                " " + " " + CONDITION[get_weather()]+ " weather delay time "+ m_requiredByWeather );
                        m_totalWeatherDelay += m_requiredByWeather;

                        String line1 ="passenger number: "+
                                String.valueOf(airEvent.get_airplane().get_passenger())+ " , total passenger number: " +String.valueOf(m_totalLandingPassenger) ;
                        AirportSim.builder.append(line1);

                        String line2 = "circling time: "+
                                String.valueOf((float)Math.round(m_circlingTime * 100) / 100)+ " , total circling time: " +String.valueOf((float)Math.round(m_totalCirclingTime * 100) / 100) +"\n" + "total fuel delay " + String.valueOf((float)Math.round(m_totalFuelDelay * 100) / 100) +
                                " , " + " total weather delay " + String.valueOf((float)Math.round(m_totalWeatherDelay * 100) / 100) + "\n";
                        AirportSim.builder.append(line2);
                        resultarray.set(2,String.valueOf((int)m_totalWeatherDelay));
                        resultarray.set(3,String.valueOf(m_totalLandingPassenger));
                        resultarray.set(0,String.valueOf((int)((float)Math.round(m_totalCirclingTime * 100) / 100)));
                        resultarray.set(1,String.valueOf((int)((float)Math.round(m_totalFuelDelay * 100) / 100)));

                        AirportEvent departureEvent = new AirportEvent(m_requiredTimeOnGround, this, AirportEvent.PLANE_DEPARTS,airEvent.get_airplane());
                        Simulator.schedule(departureEvent);

                        if(!LandingQueue.isEmpty())
                        {
                            AirportEvent landingEvent = new AirportEvent(m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED, LandingQueue.poll().get_airplane());
                            Simulator.schedule(landingEvent);
                        }
                        else {
                            m_freeToLand = true;
                        }


                        break;

                    case AirportEvent.PLANE_DEPARTS:
                        DepartingQueue.offer(airEvent);
                        if(m_freeToTakeoff){
                            AirportEvent newEventDepart = DepartingQueue.poll();
                            m_freeToTakeoff = false;
                            AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, newEventDepart.get_airplane());
                            Simulator.schedule(takeoffEvent);
                        }
                        break;

                    case AirportEvent.PLANE_TAKEOFF:
                        m_onTheGround--;
                        Airport nextAirport;
                        //reset the passenger on the plane
                        airEvent.get_airplane().set_passenger();
                        int randDestination;
                        do{ randDestination = (int)(Math.random()*5) ;}
                        while (randDestination == m_index );
                        nextAirport = airpotarray[randDestination];
                        setM_flightTime(m_index,randDestination);
                        airEvent.get_airplane().set_destination(nextAirport);
                        // set fuel and weather
                        airEvent.get_airplane().set_fuel(m_index, randDestination);


                        AirportEvent arrivalEvent = new AirportEvent(this.m_flightTime, nextAirport, AirportEvent.PLANE_ARRIVES, airEvent.get_airplane());
                        Simulator.schedule(arrivalEvent);
                        m_totalLeavingPassenger += airEvent.get_airplane().get_passenger();
                        String line =String.valueOf("------Time: " + String.valueOf((int)(Simulator.getCurrentTime()/60))+":"+String.valueOf((int)(Simulator.getCurrentTime()%60))+"------\n"+airEvent.get_airplane().get_Name()+" Departing from "+ AIRPORT[m_index] + "\n"+" onboard passengers: " + String.valueOf(airEvent.get_airplane().get_passenger())+ " total leaving passengers: " +String.valueOf(m_totalLeavingPassenger)+"\n");
                        AirportSim.builder.append(line);
                        resultarray= AirportSim.result.get(m_airportName);
                        resultarray.set(4,String.valueOf(m_totalLeavingPassenger));



                        if(!DepartingQueue.isEmpty() ) {
                            AirportEvent takeoffEvent = new AirportEvent(m_runwayTimeToTakeOff, this, AirportEvent.PLANE_TAKEOFF, DepartingQueue.poll().get_airplane());
                            Simulator.schedule(takeoffEvent);
                        }

                        else {
                            m_freeToTakeoff = true;
                        }
                        break;

            }
        }
    }

}
