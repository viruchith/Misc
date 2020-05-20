ip=list(map(int,input("Enter ip :").split(".")))
su=list(map(int,input("Enter subnet :").split(".")))
net=[] #Network ip
broad=[] #Broadcast ip
las=[] #Last usable
prefix=0 #Network prefix
hosts=1 #Number of host address
flag=0

netDict={255:8,254:7,252:6,248:5,240:4,224:3,192:2,128:1,0:0}
addrDict={255:1,254:2,252:4,248:8,240:16,224:32,192:64,128:128,0:256}

for i,s in zip(ip,su):#Network and First usable ip calculation
    if s==255:
        net.append(i)
    else:
        net.append(i&s)
    if flag==0 and s!=255:
        first.append((i&s)+1)
        flag=1
    else:
        first.append(i)
    prefix+=netDict[s]

flag=0

for i,s in zip(net,su):#Broadcast ip calculation
    if s!=255:
        broad.append((i+addrDict[s])-1)
        hosts*=addrDict[s]
    else:
        broad.append(i)
        
last=[broad[0],broad[1],broad[2],broad[3]-1]#Last usable ip calculation

def addrPrint(name,addr):
    print(name+str(addr[0])+"."+str(addr[1])+"."+str(addr[2])+"."+str(addr[3]))

addrPrint("Network : ",net)
addrPrint("Broadcast : ",broad)
addrPrint("First Host address : ",first)
addrPrint("Last Host address : ",last)
print("\nprefix : ",prefix)
print("Hosts : ",hosts-2)
