# PuzzleGame

A* algoritmus, 8-as kirakójáték:

	Feladat:
		Az A* algoritmust használva oldja meg a 8-as (n-es) kirakójátékot. Hasonlítsa össze a különböz˝o heurisztikák
		átlagos viselkedését a meglátogatott csomópontok függvényében.

	Sajnos a program nem működik parancssorból való meghívás esetén.


	Program futtatása során:
		-input: kapcsoló esetén megkell adni hogy file-ból vagy console-ból adjuk meg a bemenetet. Ha fájlból akarjuk megadni akkor file-t írunk, ha meg konzolból akkor console-t.
		-solseq: a standard kimenetre írja a teljes megoldási szekvenciát.
		-pcost: a standard kimenetre írja a megoldás költségét.
		-nvisited: a standard kimenetre írja a meglátogatott csomópontok számát.
		-cancel :  kilépés a menüből.
	Ha fájlból akarjuk megadni a kezdő állapotot a startPosition.txt fájlt kell megadni.
	
	1. Bemenet: (3*3-as mátrix formában kell megadni, másként nem megy a file-bólvaló olvasás)

		1 8 2
		0 4 3
		7 6 5 

		->költség: 17
		->meglátogatott csomópontok:7313

	2. Bemenet

		1 2 3
		4 0 5 
		8 6 7

		->költség: 18
		->meglátogatott csomópontok:35212
