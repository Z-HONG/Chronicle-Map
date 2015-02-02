/*
 * Copyright 2015 Higher Frequency Trading
 *
 *  http://www.higherfrequencytrading.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.openhft.chronicle.hash.impl.stage.hash;

import net.openhft.chronicle.hash.impl.VanillaChronicleHashHolder;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.internal.MetaBytesInterop;
import net.openhft.sg.StageRef;
import net.openhft.sg.Staged;

@Staged
public class KeyBytesInterop<K, KI, MKI extends MetaBytesInterop<K, ? super KI>> {
    @StageRef
    VanillaChronicleHashHolder<K, KI, MKI> hh;
    @StageRef ThreadLocalCopiesHolder ch;

    public final BytesReader<K> keyReader =
            hh.h().keyReaderProvider.get(ch.copies, hh.h().originalKeyReader);
    public final KI keyInterop = hh.h().keyInteropProvider.get(ch.copies, hh.h().originalKeyInterop);
    
    public MKI keyMetaInterop(K key) {
        return hh.h().metaKeyInteropProvider.get(
                ch.copies, hh.h().originalMetaKeyInterop, keyInterop, key);
    }
}