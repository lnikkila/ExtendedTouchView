ExtendedTouchView
=================

> Want some background? [Check out this blog post.][1]

<img src="https://i.imgur.com/KFJzubE.gif" width="30%" align="right" hspace="24">

Have you noticed that the Material guidelines specify that touch targets should
sometimes be larger than the actual views?

Check out this dialog’s buttons, for example:

<img src="https://i.imgur.com/pmKWxLU.png" width="50%">

Since `TouchDelegate` [is a bit clunky to use][1], `ExtendedTouchView` abstracts
it away and you can use plain ol’ XML to set those targets to any size, like
this:

```xml
<com.lnikkila.extendedtouchview.ExtendedTouchView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:touchHeight="48dp">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/button" />

</com.lnikkila.extendedtouchview.ExtendedTouchView>
```

And that’s it!

### Installation

You can get the compiled library on [JCenter][2].

Throw this in your `build.gradle`:

```groovy
compile 'com.lnikkila:extendedtouchview:0.1.0'
```

### Licensing

```
ISC License (ISC)

Copyright (c) 2015, Leo Nikkilä

Permission to use, copy, modify, and/or distribute this software for any purpose
with or without fee is hereby granted, provided that the above copyright notice
and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
THIS SOFTWARE.
```

[1]: https://lnikki.la/articles/android-extended-touch-view/
[2]: https://bintray.com/lnikkila/maven/ExtendedTouchView/view
