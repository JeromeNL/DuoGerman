package nl.joramkwetters.duogerman.data


val names = listOf(
    "Huis" to "Haus",
    "Auto" to "Auto",
    "Fiets" to "Fahrrad",
    "Trein" to "Zug",
    "Boot" to "Boot",
    "Zon" to "Sonne",
    "Maan" to "Mond",
    "Ster" to "Stern",
    "Aarde" to "Erde",
    "Lucht" to "Luft",
    "Wind" to "Wind",
    "Regen" to "Regen",
    "Sneeuw" to "Schnee",
    "IJs" to "Eis",
    "Zand" to "Sand",
    "Steen" to "Stein",
    "Boom" to "Baum",
    "Bloem" to "Blume",
    "Gras" to "Gras",
    "Blad" to "Blatt",
    "Wortel" to "Karotte",
    "Appel" to "Apfel",
    "Peer" to "Birne",
    "Banaan" to "Banane",
    "Sinaasappel" to "Orange",
    "Citroen" to "Zitrone",
    "Druif" to "Traube",
    "Kers" to "Kirsche",
    "Framboos" to "Himbeere",
    "Aardbei" to "Erdbeere",
    "Ananas" to "Ananas",
    "Perzik" to "Pfirsich",
    "Meloen" to "Melone",
    "Watermeloen" to "Wassermelone",
    "Paprika" to "Paprika",
    "Komkommer" to "Gurke",
    "Tomaat" to "Tomate",
    "Aubergine" to "Aubergine",
    "Prei" to "Lauch",
    "Selderij" to "Sellerie",
    "Ui" to "Zwiebel",
    "Knoflook" to "Knoblauch",
    "Peper" to "Pfeffer",
    "Zout" to "Salz",
    "Suiker" to "Zucker",
    "Meel" to "Mehl",
    "Boter" to "Butter",
    "Olie" to "Öl",
    "Melk" to "Milch",
    "Kaas" to "Käse",
    "Koffie" to "Kaffee",
    "Thee" to "Tee",
    "Water" to "Wasser",
    "Bier" to "Bier",
    "Wijn" to "Wein",
    "Whisky" to "Whisky",
    "Vodka" to "Wodka",
    "Rum" to "Rum",
    "Wodka" to "Wodka",
    "Gin" to "Gin",
    "Cognac" to "Cognac",
    "Champagne" to "Champagner",
    "Cola" to "Cola",
    "Sinaasappelsap" to "Orangensaft",
    "Appelsap" to "Apfelsaft",
    "Limonade" to "Limonade",
    "Mineraalwater" to "Mineralwasser",
    "Soda" to "Soda",
    "Frisdrank" to "Softdrink",
    "Kraanwater" to "Leitungswasser",
    "IJsblokje" to "Eiswürfel",
    "Koekje" to "Keks",
    "Taart" to "Kuchen",
    "Chocolade" to "Schokolade",
    "Suikerklontje" to "Zuckerwürfel",
    "Slagroom" to "Schlagsahne",
    "Mosterd" to "Senf",
    "Mayonaise" to "Mayonnaise",
    "Ketchup" to "Ketchup",
    "Azijn" to "Essig",
    "Kaneel" to "Zimt",
    "Nootmuskaat" to "Muskatnuss",
    "Kruidnagel" to "Gewürznelke",
    "Gember" to "Ingwer",
    "Pepermunt" to "Pfefferminze",
    "Koriander" to "Koriander",
    "Basilicum" to "Basilikum",
    "Oregano" to "Oregano",
    "Tijm" to "Thymian",
    "Peterselie" to "Petersilie",
    "Munt" to "Minze",
    "Rozemarijn" to "Rosmarin",
    "Lavendel" to "Lavendel",
    "Knoflook" to "Knoblauch",
    "Ui" to "Zwiebel",
    "Wortel" to "Karotte",
    "Radijs" to "Radieschen",
    "Sjalot" to "Schalotte",
    "Paprika" to "Paprika",
    "Courgette" to "Zucchini",
    "Pompoen" to "Kürbis",
    "Spinazie" to "Spinat",
    "Boerenkool" to "Grünkohl",
    "Sla" to "Salat"

).groupBy {
   it.first.first()
}.toSortedMap()
