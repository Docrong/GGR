create view v_itrequirement as
select main.id,
       main.sheetid,
       main.title,
       main.senddeptid,
       main.mainnetsystem,
       main.sendtime,
       case
           when main.mainurgentdegree = 101010201 and (link.operatetime - main.sendtime) <= 5 then 1
           when main.mainurgentdegree = 101010202
               and (link.operatetime - main.sendtime) <= 30 then 1
           else 0 end as js
from itrequirement_main main,
     itrequirement_link link
where main.id = link.mainid
  and main.status = 1
  and link.operatetype in (922, 923, 924)
