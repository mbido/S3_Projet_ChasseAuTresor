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
on calcule l'offset de la border par rapport au joueur : 
__Direction 1 :__  
```
 |#
X|
```
offset = (-1, -1)
newdir = 

__Direction 3 :__
```
#|
 |X
```

__Direction 5 :__
```
 |X
#|
```

__Direction 7 :__
```
X|
 |#
```


