# Software-engineering-lab-hw5

# شرح آزمایش

ابتدا نرم افزار Yourkit مخصوص زبان جاوا را دانلود و نصب می‌کنیم و طبق گفته کلاس، Evaluation 15 روزه را فعال می‌کنیم:
![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/0bd84592-3133-4fe1-9cf5-17e7b4142ada)

سپس روی گزینه Profile from within IDE کلیک می‌کنیم تا پلاگین Yourkit روی Intellij نصب شود:
![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/697f6eb1-3634-4e55-9996-2edbacbf3529)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/969f6a79-182b-47b6-8cc5-32182a8eadf8)

## بخش اول

حال پروژه ذکر شده را در intellij باز می‌کنیم و عمل profiling را روی فایل JavaCup اجرا می‌کنیم:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/552c01d3-c193-40d5-9700-54fad61665d3)

ورودی‌های مورد نیاز را وارد می‌کنیم و منتظر اتمام عملیات اجرا و profiling می‌شویم:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/25eaef57-a589-451b-b638-06e90c7ae360)

بعد از اتمام اجرا، تب‌های مربوط به CPU و Memory و Method list را مشاهده می‌کنیم:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/7bca46ea-f0d1-410c-8720-9216304cbfa1)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/d5283f13-6b57-4cfe-ae87-37b1c408a200)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/59be4830-46cc-4464-8140-ff5da82b90bb)

همانطور که مشخص است، در زمان اجرا، 80 درصد پردازنده سیستم اشغال شده بود و Memory مربوط به Heap نیز به خاطر اینکه در حال ایجاد یک ArrayList بزرگ بودیم در حال پر شدن بود و تابعی که بیشترین مصرف را داشته، تابع temp بوده است.

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/45d37634-8af3-4231-afa1-3c02de77063b)

یک راه برای کم‌کردن زمان اجرا و مصرف منابع این تابع این است که می‌توانیم به جای ArrayList از Array استفاده کنیم؛ چون اندازه این آرایه ثابت است و قرار نیست تغییر کند. بعد از انجام این تغییر کد به شکل زیر در می‌آید:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/c75caeca-ea78-4544-9d59-e0ad5d3e0529)

بعد از این تغییر، نتیجه profiling برنامه به این شکل خواهد بود:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/039fab93-ad2a-451c-aa1c-6f9a76beb80f)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/8e525248-4fd4-48a8-b21d-db7481f19191)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/f2894f5c-0239-4040-8b96-227d5e664022)

که بهبود برنامه را نشان می‌دهد.

## بخش دوم

در این بخش یک قطعه کد در فایل hw5.java می‌نویسیم که وظیفه نوشتن به ترتیب اعداد 1 تا 100000 را در فایلی به نام result.txt دارد. قطعه کد در ابتدا به این شکل است:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/c0da9a20-ed5d-4c3f-91db-9e77abdac58f)

حال عمل profiling را روی این کد انجام می‌دهیم:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/7f1fa740-6f8b-4ee8-9ba4-a85aed38c8da)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/1cc623e8-cd54-415c-bfd5-d83773f73739)

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/11f35eb5-3ab4-42d1-b034-80fb5ad3a9ce)

همانطور که مشاهده می‌شود، اجرای برنامه حدودا 10 ثانیه طول کشیده است و حین اجرا، 20 مگابایت حافظه و 12 درصد پردازنده درگیر شده است. همچنین تابع writeToFile بیشترین مصرف منابع را به خود اختصاص داده است؛ بنابراین باید این تابع را بهبود دهیم. حال برای بهبود عملکرد برنامه، قطعه کد را به شکل زیر تغییر می‌دهیم:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/bc8c956b-f351-44e6-b714-b9cc185b90b9)

تغییرات اعمال شده به این شکل است: به جای اینکه هر بار در حلقه فایل را باز کنیم و عدد را بنویسیم و فایل را ببندیم، رشته مورد نظر را با یک StringBuilder می‌سازیم و در آخر فقط یک بار فایل را باز می‌کنیم و کل رشته را در آن می‌نویسیم و فایل را می‌بندیم. چون عمل باز کردن فایل فرایند زمان‌بری است و تغییر یک StringBuilder با منابع و زمان بسیار کمتری انجام می‌گیرد.

حال عمل profiling را روی قطعه کد جدید انجام می‌دهیم:

![image](https://github.com/aminaryan80/Software-engineering-lab-hw5/assets/59232424/49ad7bfe-0695-4d16-a9e3-c48e2212b504)

![Uploading image.png…]()

![Uploading image.png…]()

همانطور که در تصاویر مشخص است، اختلاف میزان مصرف دو برنامه خیلی زیاد است و برنامه بهبود زیادی پیدا کرده است و زمان اجرای آن از 10 ثانیه به 31 میلی ثانیه کاهش یافته است.





