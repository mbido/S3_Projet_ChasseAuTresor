## Process :


direction :
```c
|3|2|1|
|4|X|0|
|5|6|7|
```

### Border :

Si la direction est paire `(dir % 2 == 0)`, alors la nouvelle direction est `newdir = (dir + 4) % 8`
Sinon : 
```c
|3| |1|
| |X| |
|5| |7|
```

__Direction 1 :__  
```
(Max)
 |#      #|#|# (0)
X|#        X   
 |#
```
col = Max -> newdir = 3 (dir + 2)
row = 0 -> newdir = 7 (dir - 2)

__Direction 3 :__
```
(0)
 #|      #|#|# (0)
 #|X       X
 #|
```
col = 0 -> newdir = 1 (dir - 2)
row = 0 -> newdir = 5 (dir + 2)

__Direction 5 :__
```
(0)
 #|
 #|X     X
 #|    #|#|# (Max)
```
col = 0 -> newdir = 7 (dir + 2)
row = Max -> newdir = 3 (dir - 2)

__Direction 7 :__
```
(Max)
 |#
X|#      X
 |#    #|#|# (Max)
```
col = Max -> newdir = 5 (dir - 2)
row = Max -> newdir = 1 (dir + 2)

Tableau résultant : 
```
   |00|0M|M0|MM|
| 1| X| X|+-| X|
| 3|-+| X| X| X|
| 5| X|+-| X| X|
| 7| X| X| X|-+|

```

Soient : 
	- E = {1, 3, 5, 7}
	- F = {0, 1} (0 voulant dire on interagit avec une bordure colonne, 1 une ligne )

On as alors :

$$\textbf{newdir} : E × F → E$$$$(\textbf{e}, \textbf{f}) →  (\textbf{e} + 4(1 - |\textbf{f}-\frac{(\textbf{e} \hspace{0.2cm} mod \hspace{0.2cm}4) - 1}{2}|) - 2)\hspace{0.2cm} mod \hspace{0.2cm}8$$


 